# merge-pojos

Core library to merge/patch Java Pojos.


merge-pojos is a simple and easy-to-use library which provides a neat and customizable patch/merge mechnisim for java objects (i.e., Pojos). Such fucntionality is typically used while developing webservices to support an HTTP Patch method.

For example, lets assume we have two instances of `DummyPojo.class` and we would like to patch `oldPojo` with some data from `newPojo`, here is a code snippet which shows how merge-pojos can be used to do that:

```
DummyPojo newPojo = new DummyPojo(123);
DummyPojo oldPojo = new DummyPojo(456);
MergeFactory factory = new MergeFactoryImpl();
DummyPojo resultPojo = factory.mergeFacade().merge(newPojo, oldPojo);
```

We can also define different merge rules and strategies at different granularities. For example, if we would like to merge only non-null fields of our `newPojo`, we can specify a SOURCE_NOT_NULL strategy as follows:
```
DummyPojo resultPojo = factory.mergeFacade().merge(newPojo, oldPojo);
```
