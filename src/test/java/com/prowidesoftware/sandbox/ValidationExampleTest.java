package com.prowidesoftware.sandbox;

import com.prowidesoftware.swift.model.mt.MtType;
import com.prowidesoftware.swift.scheme.Scheme;
import com.prowidesoftware.swift.scheme.SchemeXmlReader;
import com.prowidesoftware.swift.validator.SemanticProblem;
import com.prowidesoftware.swift.validator.ValidationEngine;
import com.prowidesoftware.swift.validator.ValidationProblem;
import org.junit.Test;

import java.util.List;

public class ValidationExampleTest {

    @Test
    public void test() {
        // the engine instance should be reused in the application for better performance
        ValidationEngine e = new ValidationEngine();
        e.initialize();

        // custom configuration to ignore a specific semantic rule
        e.getConfig().addIgnoredValidationProblem(SemanticProblem.D75);

        String fin = "{1:F01CCRTIT20AXXX0000143988}{2:I103ALBPPLPWXXXXN}{3:{108:YSPX39326414FXXX}{119:STP}{121:8579f4a4-a547-463e-ae63-e7c6620d59b4}}{4:\n" +
                ":20:2013112200000254\n" +
                ":23B:CRED\n" +
                ":32A:131122CHF9631,\n" +
                ":33B:EUR9681,8\n" +
                ":50F:/CH9104835058811480000\n" +
                "1/FOO NAME\n" +
                "6/CH/CRESCHZZ/053505881148\n" +
                ":52A:CRESCHZZ80A\n" +
                ":59:/PL4424945345345345345341\n" +
                "FOO KUCINA\n" +
                "65-385 FOOBAR GORA\n" +
                ":71A:BEN\n" +
                ":71F:EUR20,\n" +
                ":71F:CHF30,8\n" +
                "-}";

        // this enumeration contains the default SWIFT compliant message schemes
        Scheme scheme = MtType.MT103.scheme();

        // you can customize the default scheme object dynamically using the API
        // or you can also read an XML resource that you have already modified
        //SchemeXmlReader reader = new SchemeXmlReader();
        //reader.read(file-or-stream)

        scheme.dump(System.out);

        // if no scheme is provided, the validatio uses the default
        List<ValidationProblem> p = e.validateMtMessage(fin, scheme);

        // print validation problems found
        System.out.println(ValidationProblem.printout(p));
    }

}
