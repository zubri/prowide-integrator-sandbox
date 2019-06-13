package com.prowidesoftware.sandbox;

import com.prowidesoftware.swift.model.MtSwiftMessage;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field79;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.model.mt.mt1xx.MT199;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Initializes the memory database with a few simple MTs
 */
@Component
public class Seeder {

    @Autowired
    private MessageRepository repository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (int i=0; i<5; i++) {
            MT199 mt = new MT199("FOOBARX0XXX", "FAABARX0XXX");
            mt.append(Field20.tag("MYREFERENCE" + i));
            mt.append(Field79.tag("Narrative" + i));
            repository.save(new MtSwiftMessage(mt.getSwiftMessage()));
        }
        String fin = "{1:F01ABNABRS0AXXX8683497519}{2:O1031535051028ESPBESMMAXXX54237522470510281535N}{3:{113:ROMF}{108:0510280182794665}{119:STP}}{4:\n" +
                ":20:0061350113089908\n" +
                ":13C:/RNCTIME/1534+0000\n" +
                ":23B:CRED\n" +
                ":23E:SDVA\n" +
                ":32A:061028EUR100000,\n" +
                ":33B:EUR100000,\n" +
                ":50K:/12345678\n" +
                "MARKET AGENTS FOO ABCD123\n" +
                "AV XXXXX 123 BIS 9 PL\n" +
                "12345 BARCELONA\n" +
                ":52A:/2337\n" +
                "BCCRCRSJXXX\n" +
                ":53A:BCCRCRSJXXX\n" +
                ":57A:BNPCFR21XXX\n" +
                ":59:/ES0123456789012345671234\n" +
                "FOO AGENTES DE BOLSA ASOC\n" +
                ":71A:OUR\n" +
                ":72:/BNF/TRANSF. BCO. FOO\n" +
                "-}";
        repository.save(new MtSwiftMessage(fin));
    }

}
