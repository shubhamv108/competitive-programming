package code.shubham.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class HackerEarthAPIInvoker<Result> {

    private String formattableUrl;
    private HttpsClient<Response> httpsClient;
    public HackerEarthAPIInvoker(final String formattableUrl) {
        this.formattableUrl = formattableUrl;
        this.httpsClient = new HttpsClient<>();
    }

    public List<Result> invoke(
            final Function<Data, Result> dataResultExtractor,
            final Object... formatFieldValues) {
        List<Result> results = new ArrayList<>();
        try {
            String url = String.format(this.formattableUrl, formatFieldValues);
            int totalPages = -1, curPage = 1;
            while (totalPages == -1 || curPage <= totalPages) {
                Response response = this.httpsClient.invoke(url, Response.class);
                if (totalPages == -1) {
                    totalPages = response.getTotal_pages();
                }
                for (final Data data : response.getData()) {
                    Result result = dataResultExtractor.apply(data);
                    if (result != null) {
                        results.add(result);
                    }
                }
                curPage++;
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
