package com.dreampro.ws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Configuration
public class AppConfig {

  @Bean
  public Mapper mapper(@Value(value = "classpath*:mappings/*mappings.xml") Resource[] resourceArray)
      throws IOException {
    List<String> mappingFileUrlList = new ArrayList<>();
    for (Resource resource : resourceArray) {
      mappingFileUrlList.add(String.valueOf(resource.getURL()));
    }
    return DozerBeanMapperBuilder.create().withMappingFiles(mappingFileUrlList).build();
  }

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(64000);
    filter.setIncludeHeaders(true);
    filter.setIncludeClientInfo(true);
    return filter;
  }
}