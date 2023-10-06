package code.shubham.heapsmaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class VideoSharingPlatform {

    class Video {
        String video;
        int views = 0, likes = 0, dislikes = 0;

        Video(String video) {
            this.video = video;
        }

        String watch(int startMinute, int endMinute) {
            this.views++;
            return this.video.substring(startMinute, Math.min(endMinute + 1, this.video.length()));
        }

        void like() {
            this.likes++;
        }

        void dislike() {
            this.dislikes++;
        }
    }

    private int currVideoId = 0;
    private Queue<Integer> usedIds = new PriorityQueue<>();
    private Map<Integer, Video> videos = new HashMap<>();

    public int upload(String video) {
        final int videoId = this.getVideoId();
        videos.put(videoId, new Video(video));
        return videoId;
    }

    public void remove(int videoId) {
        this.videos.remove(videoId);
        this.usedIds.offer(videoId);
    }

    public String watch(int videoId, int startMinute, int endMinute) {
        Video video = this.videos.get(videoId);
        if (video == null)
            return "-1";
        return video.watch(startMinute, endMinute);
    }

    public void like(int videoId) {
        Video video = this.videos.get(videoId);
        if (video != null)
            video.like();
    }

    public void dislike(int videoId) {
        Video video = this.videos.get(videoId);
        if (video != null)
            video.dislike();
    }

    public int[] getLikesAndDislikes(int videoId) {
        Video video = this.videos.get(videoId);
        if (video == null)
            return new int[] {-1};
        return new int[] { video.likes, video.dislikes };
    }

    public int getViews(int videoId) {
        Video video = this.videos.get(videoId);
        if (video == null)
            return -1;
        return video.views;
    }

    private int getVideoId() {
        if (this.usedIds.isEmpty())
            return currVideoId++;
        return this.usedIds.poll();
    }
}
