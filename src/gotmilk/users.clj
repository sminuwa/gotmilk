(ns gotmilk.users
  (:use gotmilk.core
        clj-github.users
        clojure.contrib.command-line))

(defcommand "user-info"
  "Get a ton of information about a user."
  [user] (-> user show-user-info format-result))

(defcommand "follow"
  "Follow a user."
  [user] (-> user follow format-result))

(defcommand "followers"
  "Get a list of a user's followers."
  [user] (-> user show-followers format-result))

(defcommand "following"
  "Get a list of users that a user is following."
  [user] (-> user show-following format-result))

(defcommand "search-users"
  "Search for users on github. First argument should be the maximum number of results
to return"
  [n & query] (->> query (interpose " ") (apply str) search-users
                   (take (Integer/parseInt n)) (map format-result)
                   (interpose "\n") (apply str))) 