(defproject clojure-in-action "0.1.0-SNAPSHOT"
  :description "Examples of clojure in action"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot clojure-in-action.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
