package com.prowidesoftware.sandbox;

import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.wrappers.saa.XmlV2Utils;
import org.junit.Test;

import java.io.IOException;

/**
 * Example to generate an XML v2 wrapper for an MT message
 * This kind of wrapper (message format) could be used to exchange messages with the SWIFT interface
 */
public class WrapperTest {

    @Test
    public void test() throws IOException {
        String fin = "{1:F01CCRTIT20AXXX0000143988}{2:I103ALBPPLPWXXXXN}{3:{108:YSPX39326414FXXX}{119:STP}{121:8579f4a4-a547-463e-ae63-e7c6620d59b4}}{4:\n" +
                ":20:2013112200000254\n" +
                ":23B:CRED\n" +
                ":32A:131122CHF9631,\n" +
                ":33B:EUR9681,8\n" +
                ":50F:/CH9104835058811480000\n" +
                "1/BRUNO VIEILLEVIGNE\n" +
                "6/CH/CRESCHZZ/053505881148\n" +
                ":52A:CRESCHZZ80A\n" +
                ":59:/PL4424945345345345345341\n" +
                "MALNA KUCINA\n" +
                "65-385 ZIELONA GORA\n" +
                ":71A:BEN\n" +
                ":71F:EUR20,\n" +
                ":71F:CHF30,8\n" +
                "-}";
        MtSwiftMessage mt = new MtSwiftMessage(fin);
        String wrapped = XmlV2Utils.write(mt, true);
        System.out.println(wrapped);
    }

}
