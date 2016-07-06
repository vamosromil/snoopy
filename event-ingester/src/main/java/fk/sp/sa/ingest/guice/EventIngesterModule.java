package fk.sp.sa.ingest.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fk.sp.sa.ingest.service.EventIngestionConfiguration;
import fk.sp.sa.ingest.storage.DruidStorageAdaptor;
import fk.sp.sa.ingest.storage.StorageAdaptor;

import java.io.IOException;

public class EventIngesterModule extends AbstractModule {

    private final String configPath;

    public EventIngesterModule(String configPath) {
        this.configPath = configPath;
    }

    @Override
    protected void configure() {
        bind(StorageAdaptor.class).to(DruidStorageAdaptor.class);
    }

    @Provides
    @Singleton
    EventIngestionConfiguration readConfiguration() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(configPath, EventIngestionConfiguration.class);
    }
}
