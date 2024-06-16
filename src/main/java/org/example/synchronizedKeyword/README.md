synchronized keyword ensures that only one thread at a time 
executes synchronized block in a given object.(Given object means that if we have two instances of that class,
we still can call synchronized blocks from different instances at the same time but only one per each instance)

When we added a new class with static keyword, only one synchronized block can be executed at a time


We also can have both of them: static and non-static synchronized blocks, and we can 
call static and non-static blocks at the same time. However, if it is non-static blocks inside the same instance
they will be blocked.

Inside of MultipleMonitorObjects class, we can use both of the methods inside of the same instance at the same time. It can be possible,
because synchronized block refers to different objects. However, if it refered to the same object, it wouldn't
be possible.

If we look at class Reentrance, it has recursive synchronization. So what it actually does, when we call incAndGet method, method incAndGet blocks and waits until inc() is executed and only then it continues performing actions.