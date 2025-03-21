https://github.com/alxkm/articles/blob/master/src/main/java/org/alx/article/_26_scheduling_async/scheduling_async.md

to invoke a method to excute fixed time like every minute use @Scheduled
The @Scheduled annotation supports cron expressions, fixed delays, and fixed rates. 
in main add @EnableScheduling

to avoid blocking the executio can add @Async
When using the @Async annotation, Spring by default creates an Executor that has no limit on the number of threads it can create. As a result, if your job is triggered more frequently than it completes, it may lead to memory leaks due to continuously created threads.
to avoid this leaks config your Executer and SchedulingConfig
By default, Spring uses a default TaskExecutor bean for executing asynchronous methods. However, you can specify a custom Executor bean to be used instead by providing its bean name as an argument to @Async. You need to configure a custom Executor bean with the specified bean name (in this case, "jobExecutor") elsewhere in your Spring configuration.

@Async returns a String, which is not a valid return type for asynchronous methods in Spring. Asynchronous methods should return either void or a Future.