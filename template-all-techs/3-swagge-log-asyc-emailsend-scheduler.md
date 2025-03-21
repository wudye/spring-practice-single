
swagger
    springdoc.api-docs.path=/api-docs (chaneg Ip)
    configuration the GroupOpenApi and OpenAPI 

log has two way in log.xml and in application.yml
    logging.file.name=logs/app.log
    logging.file.path=logs


SchedulerConfig 
 can do in configuration class or set in application.yml file then use scheduler annotations

can work with async

Async
The @Async annotation lets us run code in the background, so our main thread can keep chugging along without waiting for slower tasks to finis
in main function 
@EnableAsync

config if have a separate threadpool for long running tasks and a separate threadpool for tasks which are more urgent and don’t take a lot of processing time.

    @Primary
    @Bean(name = "taskExecutorDefault")
    public ThreadPoolTaskExecutor taskExecutorDefault() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("Async-1-");
    executor.initialize();
    return executor;
    }

    @Bean(name = "taskExecutorForHeavyTasks")
    public ThreadPoolTaskExecutor taskExecutorRegistration() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("Async2-");
    executor.initialize();
    return executor;
    }

    use
    @Service
    public class EmailService {
        @Async("taskExecutorForHeavyTasks")
        public void sendEmailHeavy() {
            //method implementation
        }
    }

    or 
     @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncTaskExecutor() throws UnknownHostException {
        return buildPoolTaskExecutor("Demo-Async-");
    }

    @Bean("threadPoolTaskExecutor2")
    public TaskExecutor getAsyncTaskExecutor2() throws UnknownHostException {
        return buildPoolTaskExecutor("Demo-Async2-");
    }

       public TaskExecutor buildPoolTaskExecutor(String prefix) throws UnknownHostException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        InetAddress localhost = InetAddress.getLocalHost();
        int maxPoolSize = Runtime.getRuntime().availableProcessors() * 8;
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(200);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAllowCoreThreadTimeOut(false);
        executor.setThreadNamePrefix(prefix + localhost.getHostName() + "-");
        return executor;

    }

    use 
    @Async("threadPoolTaskExecutor2")

@Async and @Transcational don’t play well

email sender (JavaMailSender, MimeMessage, MimeMessageHelper)
    config in application.yml file name, pass, api..
    use it as service
    private final JavaMailSender sender;
    public void sendEmail(String fromAddress, String toAddress, String[] ccAddresses, String subject,
    String emailBody, String attachmentFileName, File file) throws EmailException
    {
       MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setText(emailBody, true);
            helper.setSubject(subject);
            if (!ArrayUtils.isEmpty(ccAddresses)) {
                helper.setCc(ccAddresses);
            }
            if (file != null && StringUtils.hasLength(attachmentFileName)) {
                helper.addAttachment(attachmentFileName, file);
            }
            sender.send(message);
    }

    

