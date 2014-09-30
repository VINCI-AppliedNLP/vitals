package gov.va.vinci.leo.listener;

import com.google.gson.JsonSyntaxException;
import gov.va.vinci.leo.tools.AuCompare;
import gov.va.vinci.leo.tools.AuStats;
import gov.va.vinci.leo.tools.LeoUtils;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.jcas.JCas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Thomas Ginter
 * Date: 12/2/13
 * Time: 4:39 PM
 */
public class AuCompareCSVListener extends BaseCsvListener {
    /**
     * Compare service object to perform the annotation comparison and store the stats.
     */
    protected AuCompare compare = null;

    /**
     * Logging object of output.
     */
    private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass().toString());

    /**
     * Constructor with a Writer.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file The printwriter to write to.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap has an invalid JSON format
     */
    public AuCompareCSVListener(Map<String, String>  auAnnotationMap, File file) throws FileNotFoundException, JsonSyntaxException {
        super(file);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file      The printwriter to write to.
     * @param separator the character to use as a separator in the csv.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String, String> auAnnotationMap, File file, char separator) throws FileNotFoundException, JsonSyntaxException {
        super(file, separator);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator and quote character.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file      The printwriter to write to.
     * @param separator the character to use as a separator in the csv.
     * @param quotechar the character to quote values with.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String, String>  auAnnotationMap, File file, char separator, char quotechar) throws FileNotFoundException, JsonSyntaxException {
        super(file, separator, quotechar);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character, and escape character.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file       The printwriter to write to.
     * @param separator  the character to use as a separator in the csv.
     * @param quotechar  the character to quote values with.
     * @param escapechar the character to escape quotechar in values.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, File file, char separator, char quotechar, char escapechar) throws FileNotFoundException, JsonSyntaxException {
        super(file, separator, quotechar, escapechar);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character, escape character, and line ending.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file       The printwriter to write to.
     * @param separator  the character to use as a separator in the csv.
     * @param quotechar  the character to quote values with.
     * @param escapechar the character to escape quotechar in values.
     * @param lineend    the string to use as line endings.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, File file, char separator, char quotechar, char escapechar, String lineend) throws FileNotFoundException, JsonSyntaxException {
        super(file, separator, quotechar, escapechar, lineend);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character,  and line ending.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param file      The printwriter to write to.
     * @param separator the character to use as a separator in the csv.
     * @param quotechar the character to quote values with.
     * @param lineend   the string to use as line endings.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, File file, char separator, char quotechar, String lineend) throws FileNotFoundException, JsonSyntaxException {
        super(file, separator, quotechar, lineend);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream The stream to write to.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream) throws FileNotFoundException, JsonSyntaxException {
        super(stream);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream    The stream to write to.
     * @param separator the character to use as a separator in the csv.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream, char separator) throws FileNotFoundException, JsonSyntaxException {
        super(stream, separator);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator and quote character.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream    The stream to write to.
     * @param separator the character to use as a separator in the csv.
     * @param quotechar the character to quote values with.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream, char separator, char quotechar) throws FileNotFoundException, JsonSyntaxException {
        super(stream, separator, quotechar);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character, and escape character.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream     The stream to write to.
     * @param separator  the character to use as a separator in the csv.
     * @param quotechar  the character to quote values with.
     * @param escapechar the character to escape quotechar in values.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream, char separator, char quotechar, char escapechar) throws FileNotFoundException, JsonSyntaxException {
        super(stream, separator, quotechar, escapechar);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character, escape character, and line ending.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream     The stream to write to.
     * @param separator  the character to use as a separator in the csv.
     * @param quotechar  the character to quote values with.
     * @param escapechar the character to escape quotechar in values.
     * @param lineend    the string to use as line endings.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream, char separator, char quotechar, char escapechar, String lineend) throws FileNotFoundException, JsonSyntaxException {
        super(stream, separator, quotechar, escapechar, lineend);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Constructor with a Writer, specifying a separator, quote character,  and line ending.
     *
     * @param auAnnotationMap JSON formatted string that maps a Gold to Tool annotation types
     * @param stream    The stream to write to.
     * @param separator the character to use as a separator in the csv.
     * @param quotechar the character to quote values with.
     * @param lineend   the string to use as line endings.
     * @throws java.io.FileNotFoundException if the file is not found or can't be written to.
     * @throws com.google.gson.JsonSyntaxException if the auAnnotationMap is not a valid JSON string.
     */
    public AuCompareCSVListener(Map<String,String> auAnnotationMap, OutputStream stream, char separator, char quotechar, String lineend) throws FileNotFoundException, JsonSyntaxException {
        super(stream, separator, quotechar, lineend);
        compare = new AuCompare(auAnnotationMap);
    }

    /**
     * Called once client initialization is complete.
     *
     * @param aStatus the status of the processing.
     * @see org.apache.uima.aae.client.UimaAsBaseCallbackListener#initializationComplete(org.apache.uima.collection.EntityProcessStatus)
     */
    @Override
    public void initializationComplete(EntityProcessStatus aStatus) {
        super.initializationComplete(aStatus);
        try {
            writeHeaders();
        } catch (IOException e) {
            log.error("Error writing out headers to the output file!", e);
        }
    }

    /**
     * Given a cas, return one or more rows of data.
     *
     * @param cas the document cas to get results from
     * @return A list of rows of CSV data. Each row is an array of strings. If no data needs
     * to be written for this CAS, you should return an empty list.
     */
    @Override
    protected List<String[]> getRows(CAS cas) {
        try {
            JCas jCas = cas.getJCas();
            return compare.annotationComparison(jCas);
        } catch (CASException e) {
            log.error("Error getting JCas reference!", e);
            return new ArrayList<String[]>();
        }
    }

    /**
     * Returns the string list of the headers for a row.
     * ie ("col1", "id", "myValue");
     *
     * @return the string list of the headers for a row.
     */
    @Override
    protected String[] getHeaders() {
        return new String[]{"documentID", "mapping", "begin", "end", "type", "coveredText", "statClass"};
    }

    /**
     * Write the stat string for each AuStats object out to the console.
     */
    public void outputStatsToConsole() {
        if(compare == null) {
            return;
        }
        compare.outputStatsToConsole();
    }

    /**
     * Return a list of the stat String objects for each AuStats object.
     *
     * @return list of stat Strings
     */
    public List<String> outputStatStrings() {
        if(compare == null) {
            return null;
        }
        return compare.outputStatStrings();
    }

    /**
     * Return a list of AuStats objects used in the compare service accessed by the gov.va.vinci.leo.listener.
     *
     * @return list of AuStats objects
     */
    public List<AuStats> getStatList() { return compare.getStatList(); }
}
