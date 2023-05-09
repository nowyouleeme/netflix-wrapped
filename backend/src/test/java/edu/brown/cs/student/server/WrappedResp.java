package edu.brown.cs.student.server;

import edu.brown.cs.student.main.csv.data.Data;

/**
 * record used in testing to parse response from wrapped endpoint
 * @param result success
 * @param report the wrapped report associated that was successfully generated
 */
public record WrappedResp(String result, Data.WrappedData report){}

