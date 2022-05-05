package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenPairWithCommentAndEmptyStrings() {
        String path = "./data/appEmptyStrings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBrokenTemplate() throws IllegalArgumentException {
        String path = "./data/appBrokenTemplate.properties";
        Config config = new Config(path);
        config.load();
    }
}