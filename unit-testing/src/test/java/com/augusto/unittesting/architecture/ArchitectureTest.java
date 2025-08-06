package com.augusto.unittesting.architecture;

import com.augusto.unittesting.config.UnitTestBase;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class ArchitectureTest extends UnitTestBase {

    JavaClasses classes = new ClassFileImporter().importPackages("com.augusto.unittesting");

    @Test
    void shouldCheckDependencyBetweenControllerAndRepository() {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(classes);
    }

    @Test
    void shouldCheckDependencyBetweenControllerAndService() {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..service..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(classes);
    }

    @Test
    void shouldCheckIfTestClassesExtendUnitTestBase() {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..unittesting..")
                .and().haveSimpleNameEndingWith("Test")
                .should().beAssignableTo(com.augusto.unittesting.config.UnitTestBase.class)
                .check(classes);
    }

}
