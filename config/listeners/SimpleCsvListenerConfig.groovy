import gov.va.vinci.vitals.listeners.SimpleListener
import gov.va.vinci.vitals.types.Height_Term
import gov.va.vinci.vitals.types.Hr_Term

String csvDirPath = "output/";

String csvFile = "simple-output.csv";

if (!(new File(csvDirPath).exists()))
    new File(csvDirPath).mkdirs();

listener = new SimpleListener(new File(csvDirPath + "/" + csvFile), true,
        [
                Hr_Term.canonicalName,
                gov.va.vinci.vitals.types.Age_Term.canonicalName
        ] as String[]);