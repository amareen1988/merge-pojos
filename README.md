# merge-pojos

Core library to merge/patch Java Pojos.


merge-pojos is a simple and easy-to-use library which provides a neat and customizable patch/merge mechnisim for java objects (i.e., Pojos). Such fucntionality is typically used while developing webservices to support an HTTP Patch method.

For example, lets assume we have two instances of `DummyPojo.class` and we would like to patch `oldPojo` with some data from `newPojo`, here is a code snippet which shows how merge-pojos can be used to do that:

```
DummyPojo newPojo = new DummyPojo(123);
DummyPojo oldPojo = new DummyPojo(456);
MergeFactory factory = new MergeFactoryImpl();
factory.mergeFacade().merge(newPojo, oldPojo);
```
