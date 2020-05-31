package com.jacinto.fas;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.jacinto.fas");

        noClasses()
            .that()
            .resideInAnyPackage("com.jacinto.fas.service..")
            .or()
            .resideInAnyPackage("com.jacinto.fas.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.jacinto.fas.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
