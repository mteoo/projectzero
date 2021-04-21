package com.matheus.dias.projectzero.models;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.matheus.dias.projectzero.core.Classes;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

public class AllModelsTestLombok {

    private Set<Class<?>> models =  new HashSet<>();

    @Before
    public void populaSetModels() throws Exception {
        ImmutableSet<ClassPath.ClassInfo> classes = Classes.findAllAnnotated("com.matheus.dias.projectzero.models", Entity.class);
        classes.stream().forEach(c -> {
            models.add(c.load());
        });
    }

    @Test
    public void deveTestarTodosModelsAnotadosComEntity() {
        //Esse teste genérico pode ser usado em casos que o uso do Lombok gera uma baixa ccobertura no SonarQube.
        models.forEach(view -> {
            EqualsVerifier.forClass(view).withRedefinedSuperclass().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED, Warning.STRICT_INHERITANCE).usingGetClass().verify();
        });

    }

}
