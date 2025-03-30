package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.File;

class AssumptionTests {
    @Test
    void testAsumptionMethod() {
        final File file = new File("hello.txt");
        Assumptions.assumeTrue(file.exists(), "File not found. The test case skipped.");
    }
}
