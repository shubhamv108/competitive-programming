package code.shubham;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    class Twitter {
        UserManager userManager = new UserManager();
        int tweetCounter = 0;

        public void postTweet(int userId, int tweetId) {
            this.userManager.createOrGetUser(userId)
                    .addTweet(new Tweet(tweetCounter++, tweetId));
        }

        public List<Integer> getNewsFeed(int userId) {
            return this.userManager.createOrGetUser(userId)
                    .getNewsFeed();
        }

        public void follow(int followerId, int followeeId) {
            this.userManager.createOrGetUser(followerId)
                    .addFollowing(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            this.userManager.createOrGetUser(followerId)
                    .removeFollowing(followeeId);
        }

        class Tweet {
            int number;
            int tweetId;

            Tweet(int number, int tweetId) {
                this.number = number;
                this.tweetId = tweetId;
            }

            int getNumber() {
                return number;
            }
        }

        class UserManager {
            HashMap<Integer, User> users = new HashMap<>();

            User createOrGetUser(int userId) {
                return this.users.computeIfAbsent(userId, id -> new User());
            }
        }

        class User {
            ArrayList<Tweet> tweets = new ArrayList<>();
            HashSet<Integer> following = new HashSet<>();

            void addTweet(Tweet tweet) {
                this.tweets.addFirst(tweet);
            }

            void addFollowing(int follow) {
                this.following.add(follow);
            }

            void removeFollowing(int follow) {
                this.following.remove(follow);
            }

            List<Integer> getNewsFeed() {
                PriorityQueue<Tweet> q = new PriorityQueue<>((x, y) -> y.getNumber() - x.getNumber());

                this.tweets.stream().limit(10).forEach(q::add);
                this.following
                        .stream()
                        .map(Twitter.this.userManager::createOrGetUser)
                        .map(User::getTweets)
                        .limit(10)
                        .forEach(q::addAll);

                ArrayList<Integer> result = new ArrayList<>(10);
                while (!q.isEmpty() && result.size() != 10)
                    result.add(q.poll().tweetId);

                return result;
            }

            List<Tweet> getTweets() {
                return tweets;
            }

            // public static void main(String[] args) {
            //     Twitter obj = new Twitter();
            //     obj.postTweet(1, 5);
            //     System.out.println(obj.getNewsFeed(1));
            //     obj.follow(1, 2);
            //     obj.postTweet(2, 6);
            //     System.out.println(obj.getNewsFeed(1));
            //     obj.unfollow(1, 2);
            //     System.out.println(obj.getNewsFeed(5));
            // }
        }
    }
}

class Solution2 {
    class Twitter {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        HashMap<Integer, HashSet<Integer>> follow = new HashMap<>();

        public void postTweet(int userId, int tweetId) {
            map.put(tweetId, userId);
        }

        public List<Integer> getNewsFeed(int userId) {
            LinkedList<Integer> result = new LinkedList<>();
            ArrayList<Integer> reverseOrderedKeys = new ArrayList<>(map.keySet());
            Collections.reverse(reverseOrderedKeys);
            int count = 0;
            for (int i : reverseOrderedKeys) {
                if ((map.get(i) == userId ||
                        (follow.containsKey(userId)
                                && follow.get(userId).contains(map.get(i)))) && count < 10) {
                    result.add(i);
                    ++count;
                }
            }
            return result;
        }

        public void follow(int followerId, int followeeId) {
            follow.computeIfAbsent(followerId, id -> new HashSet<>())
                    .add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            follow.getOrDefault(followerId, new HashSet<>())
                    .remove(followeeId);
        }
    }
}