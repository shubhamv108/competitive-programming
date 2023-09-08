package code.shubham.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HackerEarthConcurrentAPIInvoker<Result> {

    private String formattableUrl;
    private final HttpsClient<Response> httpsClient;

    private int threadCount;


    public HackerEarthConcurrentAPIInvoker(final String formattableUrl) {
        this(formattableUrl, 10);
    }

    public HackerEarthConcurrentAPIInvoker(final String formattableUrl, final int threadCount) {
        this.formattableUrl = formattableUrl + "&page=%s";
        this.httpsClient = new HttpsClient<>();
        this.threadCount = threadCount;
    }

    public List<Result> invokeConcurrently(final Function<Data, Result> dataResultExtractor, final int pages, final Object... formatFieldValues) {
        List<Future<List<Result>>> results = null;
        try {
            List<Callable<List<Result>>> tasks = getTasks(dataResultExtractor, pages);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            results = executorService.invokeAll(tasks);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return results.stream().map(f -> {
            try {
                return f.get();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            return new ArrayList<Result>();
        }).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    private List<Callable<List<Result>>> getTasks(final Function<Data, Result> dataResultExtractor, int pages) {
        return IntStream.rangeClosed(1, pages).mapToObj(page -> this.getTask(dataResultExtractor, page)).collect(Collectors.toList());
    }

    private Callable<List<Result>> getTask(final Function<Data, Result> dataResultExtractor, int page) {
        return () -> {
            List<Result> results = new ArrayList<>();
            String url = String.format(this.formattableUrl, page);
            System.out.println(url);
            Response response = this.httpsClient.invoke(url, Response.class);
            if (response == null)
                return results;

            for (final Data data : response.getData()) {
                Result result = dataResultExtractor.apply(data);
                if (result != null)
                    results.add(result);
            }
            return results;
        };
    }
}




