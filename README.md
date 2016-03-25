# merge-pojos

Core library to merge/patch Java Pojos.


This is an easy to use library that provides a simple and customizable patching/merging mechnisim for java objects (i.e., Pojos).

Lets assume we have two instances of `DummyPojo.class` and we would like to patch `oldPojo` with some data from `newPojo`, here is a code snippet which shows how merge-pojos can be used to do that:

```
DummyPojo newPojo = new DummyPojo(123);
DummyPojo oldPojo = new DummyPojo(456);
MergeFactory factory = new MergeFactoryImpl();
factory.mergeFacade().merge(newPojo, oldPojo);
```
