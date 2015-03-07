# Rails test.check Example

This is the companing project for my talk on [Property
Testing](https://speakerdeck.com/andrewhr/property-based-testing-on-rails). This
repository contains a very simple Rails application where you will be
able to add entries of financial tansactions for our hypothetical 
Money Tracking application.

## Dependencies

To run the Rails application:

- ruby
- bundler
- postgresql

To run the Clojure test suite:

- java jdk
- [leiningen](http://leiningen.org), think Rake + Bundler
- [phantomjs](http://phantomjs.org), our default webdriver

## The test.check suite

Everything here is pretty common for a Rails application. The action
really happens on the `test` folder. There you will find some Clojure 
namespaces with sample code:

 - on `samples` namespace we will find the implementation of the examples
   for addition and sort, presented as pseudo-code on the slides.
 - on `rails` namespace there will be a more complex example, that will
   do all the boilerplate need to do a "capybara integration style"
   testing using Clojure.

## Running the tests

To run the Rails app in test mode, just do:

```console
rails server -e test
```

After that, you could run the Clojure suite via leiningen:

```console
lein test
```

Please note that to run the `rails` namespace the Rails server *must* be
running.
