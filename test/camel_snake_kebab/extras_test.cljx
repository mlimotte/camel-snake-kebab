(ns camel-snake-kebab.extras-test
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :refer :all]
            #+clj  [clojure.test :refer :all]
            #+cljs [cemerick.cljs.test :as t])
  #+cljs (:require-macros [cemerick.cljs.test :refer [deftest is testing are]]))

(deftest transform-keys-test
  (are [x y] (= x (transform-keys csk/->kebab-case-keyword y))
    nil                            nil
    {}                             {}
    []                             []
    {:total-books 0 :all-books []} {'total_books 0 "allBooks" []}
    [{:the-author "Dr. Seuss" :the-title "Green Eggs and Ham"}]
    [{'the-Author "Dr. Seuss" "The_Title" "Green Eggs and Ham"}]
    {:total-books 1 :all-books [{:the-author "Dr. Seuss" :the-title "Green Eggs and Ham"}]}
    {'total_books 1 "allBooks" [{'THE_AUTHOR "Dr. Seuss" "the_Title" "Green Eggs and Ham"}]}))
