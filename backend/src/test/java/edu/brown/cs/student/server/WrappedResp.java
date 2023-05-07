package edu.brown.cs.student.server;

import edu.brown.cs.student.main.csv.data.Data;

public record WrappedResp(String result, Data.WrappedData report){}

