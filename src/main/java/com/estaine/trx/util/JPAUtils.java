package com.estaine.trx.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;


import static org.hibernate.cfg.AvailableSettings.*;

/**
 * Created by estaine on 30.11.2018.
 */
@Slf4j
public class JPAUtils {

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            Properties properties = new Properties();
            try (InputStream propsStream = JPAUtils.class.getResourceAsStream("/application.properties")) {
                properties.load(propsStream);
                Map datasourceProps = buildMySQLProps(properties);

                factory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                        getPersistenceUnitInfo(),
                        datasourceProps
                );
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return factory;
    }

    private static Map buildMySQLProps(Properties properties) {
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String driver = properties.getProperty("spring.datasource.driver-class-name");

        return ImmutableMap.<String, Object>builder()
                .put(DRIVER, driver)
                .put(URL, url)
                .put(DIALECT, "org.hibernate.dialect.MySQL5Dialect")
                .put("javax.persistence.jdbc.user", username)
                .put("javax.persistence.jdbc.password", password)
                .put("hibernate.temp.use_jdbc_metadata_defaults", false)
                .put(SHOW_SQL, true)
                .put(QUERY_STARTUP_CHECKING, false)
                .put(GENERATE_STATISTICS, false)
                .put(USE_REFLECTION_OPTIMIZER, false)
                .put(USE_SECOND_LEVEL_CACHE, false)
                .put(USE_QUERY_CACHE, false)
                .put(USE_STRUCTURED_CACHE, false)
                .put(STATEMENT_BATCH_SIZE, 20)
                .build();
    }

    private static PersistenceUnitInfo getPersistenceUnitInfo() {
        return new PersistenceUnitInfo() {
            @Override
            public String getPersistenceUnitName() {
                return null;
            }

            @Override
            public String getPersistenceProviderClassName() {
                return null;
            }

            @Override
            public PersistenceUnitTransactionType getTransactionType() {
                return null;
            }

            @Override
            public DataSource getJtaDataSource() {
                return null;
            }

            @Override
            public DataSource getNonJtaDataSource() {
                return null;
            }

            @Override
            public List<String> getMappingFileNames() {
                return null;
            }

            @Override
            public List<URL> getJarFileUrls() {
                return null;
            }

            @Override
            public URL getPersistenceUnitRootUrl() {
                return null;
            }

            @Override
            public List<String> getManagedClassNames() {
                try {
                    return ClassPath.from(Thread.currentThread().getContextClassLoader())
                            .getTopLevelClassesRecursive("com.estaine.trx.model")
                            .stream()
                            .map(ClassPath.ClassInfo::getName)
                            .collect(Collectors.toList());
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean excludeUnlistedClasses() {
                return false;
            }

            @Override
            public SharedCacheMode getSharedCacheMode() {
                return null;
            }

            @Override
            public ValidationMode getValidationMode() {
                return null;
            }

            @Override
            public Properties getProperties() {
                return null;
            }

            @Override
            public String getPersistenceXMLSchemaVersion() {
                return null;
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;
            }

            @Override
            public void addTransformer(ClassTransformer transformer) {

            }

            @Override
            public ClassLoader getNewTempClassLoader() {
                return null;
            }
        };
    }
}
