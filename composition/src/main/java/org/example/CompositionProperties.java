package org.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "composition")
public class CompositionProperties {

    @Value("${composition.threshold:0}")
    private float threshold;

}
