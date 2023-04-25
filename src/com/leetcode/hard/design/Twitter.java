package com.leetcode.hard.design;

import java.util.*;

class Twitter {

    Map<Integer, Set<Integer>> followers;
    Map<Integer, List<Integer>> userTweets;
    Map<Integer, TweetInfo> newsFeed;
    /** Initialize your data structure here. */
    public Twitter() {
        followers = new HashMap<>();
        userTweets = new HashMap<>();
        newsFeed = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        userTweets.computeIfAbsent(userId, x -> new ArrayList<>()).add(tweetId);
        for(Integer follower : followers.get(userId)) {
            newsFeed.putIfAbsent(follower, new TweetInfo());
            newsFeed.get(follower).addTweet(tweetId);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        return newsFeed.get(userId).tweetIds;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        followers.computeIfAbsent(followerId, x-> new HashSet<>()).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followers.containsKey(followerId) && !followers.get(followerId).isEmpty()) {
            followers.get(followerId).remove(followeeId);
        }
    }
    
    class TweetInfo {
        List<Integer> tweetIds;
        int last_index;
        TweetInfo() {
            tweetIds = new ArrayList<>();
            last_index = 0;
        }
        public void addTweet(int tweetId) {
            if(tweetIds.size() == 10) {
                tweetIds.set(((last_index++)%10), tweetId);
            } else {
                tweetIds.add(last_index++, tweetId);
            }
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */