package batch.jobs.configuration;

public final class JobsConfigurationFactory {

    public static Class<?> getJobConfiguration(String jobName) {

        String jobsConfigurationPackage = "batch.jobs.configuration";
        Class<?> jobConfig = null;

        try {
            jobConfig = Class.forName(jobsConfigurationPackage + "." + jobName.substring(0, 1).toUpperCase()
                    + jobName.substring(1) + "Config");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jobConfig;
    }
}
