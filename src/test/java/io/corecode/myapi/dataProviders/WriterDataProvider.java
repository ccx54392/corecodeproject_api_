package io.corecode.myapi.dataProviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.entity.Writer;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WriterDataProvider {

    @DataProvider(name = "persistWriters")
    public Iterator<Object[]> persistWriters() throws Exception {
        Writer writer = new Writer(); //**
        writer.setName("new writer"); //**
        String jsonWriter = new ObjectMapper().writeValueAsString(writer);//**

        List<Object[]> values = new ArrayList<Object[]>();
        values.add(new Object[]{jsonWriter, 201});
        return values.iterator();
    }
}
