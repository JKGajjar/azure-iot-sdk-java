/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service;

import com.microsoft.azure.sdk.iot.deps.serializer.JobPropertiesParser;

import java.util.Date;

public class JobProperties
{
    public JobProperties()
    {
        this.setJobIdFinal("");
    }

    /**
     * @return the system generated job id. Ignored at creation.
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId the job id
     * @throws IllegalArgumentException if the provided jobId is null
     */
    @Deprecated
    public void setJobId(String jobId) throws IllegalArgumentException
    {
        //Codes_SRS_SERVICE_SDK_JAVA_JOB_PROPERTIES_34_004: [If the provided jobId is null, an IllegalArgumentException shall be thrown.]
        if (jobId == null)
        {
            throw new IllegalArgumentException("jobId cannot be null");
        }

        this.jobId = jobId;
    }

    /**
     * @param jobId the job id
     * @throws IllegalArgumentException if the provided jobId is null
     */
    public final void setJobIdFinal(String jobId) throws IllegalArgumentException
    {
        //Codes_SRS_SERVICE_SDK_JAVA_JOB_PROPERTIES_34_004: [If the provided jobId is null, an IllegalArgumentException shall be thrown.]
        if (jobId == null)
        {
            throw new IllegalArgumentException("jobId cannot be null");
        }

        this.jobId = jobId;
    }

    /**
     * @return the system generated UTC job start time. Ignored at creation.
     */
    public Date getStartTimeUtc() {
        return startTimeUtc;
    }

    /**
     * @param startTimeUtc the UTC job start time.
     */
    public void setStartTimeUtc(Date startTimeUtc) {
        this.startTimeUtc = startTimeUtc;
    }

    /**
     * @return the UTC job end time. Ignored at creation.
     * Represents the time the job stopped processing.
     */
    public Date getEndTimeUtc() {
        return endTimeUtc;
    }

    /**
     * @param endTimeUtc the UTC job end time.
     */
    public void setEndTimeUtc(Date endTimeUtc) {
        this.endTimeUtc = endTimeUtc;
    }

    /**
     * @return the type of job to execute.
     */
    public JobType getType() {
        return type;
    }

    /**
     * @param type the type of job to execute.
     */
    public void setType(JobType type) {
        this.type = type;
    }

    /**
     * @return the system generated job status. Ignored at creation.
     */
    public JobStatus getStatus() {
        return status;
    }

    /**
     * @param status the job status.
     */
    public void setStatus(JobStatus status) {
        this.status = status;
    }

    /**
     * @return the system generated job progress. Ignored at creation.
     * Represents the completion percentage.
     */
    public int getProgress() {
        return progress;
    }

    /**
     * @param progress the job progress.
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * @return URI containing SAS token to a blob container that contains registry data to sync.
     */
    public String getInputBlobContainerUri() {
        return inputBlobContainerUri;
    }

    /**
     * @param inputBlobContainerUri the input blob container URI.
     */
    public void setInputBlobContainerUri(String inputBlobContainerUri) {
        this.inputBlobContainerUri = inputBlobContainerUri;
    }

    /**
     * @return URI containing SAS token to a blob container.
     * This is used to output the status of the job and the results.
     */
    public String getOutputBlobContainerUri() {
        return outputBlobContainerUri;
    }

    /**
     * @param outputBlobContainerUri the output blob container URI.
     */
    public void setOutputBlobContainerUri(String outputBlobContainerUri) {
        this.outputBlobContainerUri = outputBlobContainerUri;
    }

    /**
     * @return whether the keys are included in export or not.
     */
    public boolean getExcludeKeysInExport() {
        return excludeKeysInExport;
    }

    /**
     * @param excludeKeysInExport optional for export jobs; ignored for other jobs.  Default: false.
     * If false, authorization keys are included in export output.  Keys are exported as null otherwise.
     */
    public void setExcludeKeysInExport(boolean excludeKeysInExport) {
        this.excludeKeysInExport = excludeKeysInExport;
    }

    /**
     * @return System generated. Ignored at creation.
     * If status == failure, this represents a string containing the reason.
     */
    public String getFailureReason() {
        return failureReason;
    }

    /**
     * @param failureReason the failure reason.
     */
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public enum JobType
    {
        UNKNOWN,
        EXPORT,
        IMPORT
    }

    public enum JobStatus
    {
        UNKNOWN,
        ENQUEUED,
        RUNNING,
        COMPLETED,
        FAILED,
        CANCELLED
    }

    // CODES_SRS_SERVICE_SDK_JAVA_JOB_PROPERTIES_34_001: [The JobProperties class shall have the following properties: jobId,
    // startTimeUtc, endTimeUtc, JobType, JobStatus, progress, inputBlobContainerUri, outputBlobContainerUri,
    // excludeKeysInExport, failureReason.]
    private String jobId;
    private Date startTimeUtc;
    private Date endTimeUtc;
    private JobType type;
    private JobStatus status;
    private int progress;
    private String inputBlobContainerUri;
    private String outputBlobContainerUri;
    private boolean excludeKeysInExport;
    private String failureReason;

    /**
     * Constructs a new JobProperties object using a JobPropertiesParser object
     * @param parser the parser object to convert from
     */
    JobProperties(JobPropertiesParser parser)
    {
        //Codes_SRS_SERVICE_SDK_JAVA_JOB_PROPERTIES_34_003: [This method shall convert the provided parser into a JobProperty object and return it.]
        this.endTimeUtc = parser.getEndTimeUtc();
        this.excludeKeysInExport = parser.isExcludeKeysInExport();
        this.failureReason = parser.getFailureReason();
        this.inputBlobContainerUri = parser.getInputBlobContainerUri();
        this.outputBlobContainerUri = parser.getOutputBlobContainerUri();
        this.jobId = parser.getJobIdFinal();
        this.progress = parser.getProgress();
        this.startTimeUtc = parser.getStartTimeUtc();

        if (parser.getStatus() != null)
        {
            this.status = JobStatus.valueOf(parser.getStatus().toUpperCase());
        }

        if (parser.getType() != null)
        {
            this.type = JobType.valueOf(parser.getType().toUpperCase());
        }
    }

    /**
     * Converts this into a JobPropertiesParser object that can be used for serialization and deserialization
     * @return the converted JobPropertiesParser object
     */
    JobPropertiesParser toJobPropertiesParser()
    {
        //Codes_SRS_SERVICE_SDK_JAVA_JOB_PROPERTIES_34_002: [This method shall convert this into a JobPropertiesParser object and return it.]
        JobPropertiesParser jobPropertiesParser = new JobPropertiesParser();
        jobPropertiesParser.setEndTimeUtc(this.endTimeUtc);
        jobPropertiesParser.setExcludeKeysInExport(this.excludeKeysInExport);
        jobPropertiesParser.setFailureReason(this.failureReason);
        jobPropertiesParser.setInputBlobContainerUri(this.inputBlobContainerUri);
        jobPropertiesParser.setOutputBlobContainerUri(this.outputBlobContainerUri);
        jobPropertiesParser.setJobId(this.jobId);
        jobPropertiesParser.setProgress(this.progress);
        jobPropertiesParser.setStartTimeUtc(this.startTimeUtc);

        if (this.status != null)
        {
            jobPropertiesParser.setStatus(this.status.toString());
        }

        if (this.type != null)
        {
            jobPropertiesParser.setType(this.type.toString().toLowerCase());
        }

        return jobPropertiesParser;
    }
}