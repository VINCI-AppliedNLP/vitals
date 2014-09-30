package gov.va.vinci.leo.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * Track the stats for annotation comparison between a gold (Au) standard annotation and a tool generated one.
 *
 * User: Thomas Ginter
 * Date: 11/18/13
 * Time: 3:51 PM
 */
public class AuStats {
    /**
     * Canonical name of the gold annotation.
     */
    protected String auAnnotation   = null;
    /**
     * Canonical name of the tool annotation.
     */
    protected String toolAnnotation = null;
    /**
     * Count of false positive instances.
     */
    protected double falsePositive = 0;
    /**
     * Count of true positive instances.
     */
    protected double truePositive  = 0;
    /**
     * Count of false negative instances.
     */
    protected double falseNegative = 0;
    /**
     * Count of loose true positive instances.
     */
    protected double looseTruePositive = 0;
    /**
     * Count of loose false positive instances.
     */
    protected double looseFalsePositive = 0;
    /**
     * Count of loose false negative instances.
     */
    protected double looseFalseNegative = 0;

    /******************************
     * Class Definition Constants
     ******************************/

    /**
     * True Positive.
     */
    public static final String TRUE_POSITIVE        = "TP";
    /**
     * False Positive.
     */
    public static final String FALSE_POSITIVE       = "FP";
    /**
     * False Negative.
     */
    public static final String FALSE_NEGATIVE       = "FN";
    /**
     * Loose True Positive.
     */
    public static final String LOOSE_TRUE_POSITIVE  = "LTP";
    /**
     * Loose False Positive.
     */
    public static final String LOOSE_FALSE_POSITIVE = "LFP";
    /**
     * Loose False Negative.
     */
    public static final String LOOSE_FALSE_NEGATIVE = "LFN";

    /**
     * Default constructor, initialize annotation names to empty strings.
     */
    public AuStats() {
        auAnnotation   = "";
        toolAnnotation = "";
    }

    /**
     * Initialize the gold and tool annotation names.
     *
     * @param auAnnotation the canonical name of the gold annotation
     * @param toolAnnotation the canonical name of the tool annotation
     */
    public AuStats(String auAnnotation, String toolAnnotation) {
        this.auAnnotation   = auAnnotation;
        this.toolAnnotation = toolAnnotation;
    }

    /**
     * Add one to the count of exact match occurrences.
     *
     * @return String array of stats classes that were incremented
     */
    public String[] addExactMatch() {
        looseTruePositive++;
        truePositive++;
        return new String[] {LOOSE_TRUE_POSITIVE, TRUE_POSITIVE};
    }

    /**
     * Add a count of n to the exact match occurrences.
     *
     * @param numMatches number n to increment the exact match occurrences
     * @return String array of stats classes that were incremented
     */
    public String[] addExactMatch(int numMatches) {
        looseTruePositive += numMatches;
        truePositive      += numMatches;
        return new String[] {LOOSE_TRUE_POSITIVE, TRUE_POSITIVE};
    }

    /**
     * Add one to the count of loose match, overlap only, occurrences.
     *
     * @return String array of stats classes that were incremented
     */
    public String[] addOverlapMatch() {
        looseTruePositive++;
        falsePositive++;
        falseNegative++;
        return new String[] {LOOSE_TRUE_POSITIVE, FALSE_POSITIVE, FALSE_NEGATIVE};
    }

    /**
     * Add a count of n to the loose match, overlap only, occurrences.
     *
     * @param numOverlaps number n to increment the loose occurrences
     * @return String array of stats classes that were incremented
     */
    public String[] addOverlapMatch(int numOverlaps) {
        looseTruePositive += numOverlaps;
        falsePositive     += numOverlaps;
        falseNegative     += numOverlaps;
        return new String[] {LOOSE_TRUE_POSITIVE, FALSE_POSITIVE, FALSE_NEGATIVE};
    }

    /**
     * Add one to the ocunt of gold only, no tool match or overlap, occurrences.
     *
     * @return String array of stats classes that were incremented
     */
    public String[] addNoMatch() {
        falseNegative++;
        looseFalseNegative++;
        return new String[] {FALSE_NEGATIVE, LOOSE_FALSE_NEGATIVE};
    }

    /**
     * Add a count of n to the gold only, no tool match or overlap, occurrences.
     *
     * @param numNoMatches number n to increment the gold only occurrences
     * @return String array of stats classes that were incremented
     */
    public String[] addNoMatch(int numNoMatches)  {
        falseNegative += numNoMatches;
        looseFalseNegative += numNoMatches;
        return new String[] {FALSE_NEGATIVE, LOOSE_FALSE_NEGATIVE};
    }

    /**
     * Add one to the count of tool only, no gold match or overlap, annotation occurrences.
     *
     * @return String array of stats classes that were incremented
     */
    public String[] addToolOnly() {
        falsePositive++;
        looseFalsePositive++;
        return new String[] {FALSE_POSITIVE, LOOSE_FALSE_POSITIVE};
    }

    /**
     * Add a count n to the tool only, no gold match or overlap, annotation occurrences.
     *
     * @param numToolOnly number n to increment the tool only occurrences
     * @return String array of stats classes that were incremented
     */
    public String[] addToolOnly(int numToolOnly) {
        falsePositive      += numToolOnly;
        looseFalsePositive += numToolOnly;
        
        return new String[] {FALSE_POSITIVE, LOOSE_FALSE_POSITIVE};
    }

    /**
     * String representation of the Stats object results.
     *
     * @return String representation of the Stats object
     */
    @Override
    public String toString() {
        String out = auAnnotation + " -> " + toolAnnotation;
        if((truePositive + falsePositive) == 0 || (truePositive + falseNegative) == 0) {
            out += ": No Stats to Report";
            return out;
        }
        double precision = truePositive / (truePositive + falsePositive);
        double recall = truePositive / (truePositive + falseNegative);
        double f = ((precision + recall) > 0)? 2 * (precision * recall)/(precision + recall) : 0.0;
        double lp = looseTruePositive / (looseTruePositive + looseFalsePositive);
        double lr = looseTruePositive / (looseTruePositive + looseFalseNegative);
        double lf = ((lp + lr) > 0)? 2 * (lp * lr) / (lp + lr) : 0.0;
        out += "\nTP\tFP\tFN\tPrecision\tRecall\tF1";
        out += "\n" + truePositive + "\t" + falsePositive + "\t" + falseNegative;
        out += "\t" + precision + "\t" + recall + "\t" + f;
        out += "\nLTP\tLFP\tLFN\tLPrecision\tLRecall\tLF1";
        out += "\n" + looseTruePositive + "\t" + looseFalsePositive + "\t" + looseFalseNegative;
        out += "\t" + lp + "\t" + lr + "\t" + lf;

        return out;
    }

    /**
     * Gold Annotation Name.
     *
     * @return Copy of the gold annotation name String representation in this object
     *          or null if no gold annotation name has been set.
     */
    public String getAuAnnotation() {
        if(StringUtils.isBlank(auAnnotation)) { return null; }
        return String.copyValueOf(auAnnotation.toCharArray());
    }

    /**
     * Tool annotation name.
     *
     * @return Copy of the tool annotation name String representation in this object
     *          or null if no tool name has been set.
     */
    public String getToolAnnotation() {
        if(StringUtils.isBlank(toolAnnotation)) { return null; }
        return String.copyValueOf(toolAnnotation.toCharArray());
    }

    /**
     * Number of False Positive instances.
     *
     * @return double representing the number of false positive instances
     */
    public double getFalsePositive() {
        return falsePositive;
    }

    /**
     * Number of True Positive instances.
     *
     * @return double representing the number of true positive instances
     */
    public double getTruePositive() {
        return truePositive;
    }

    /**
     * Number of False Negative instances.
     *
     * @return double representing the false negative instances
     */
    public double getFalseNegative() {
        return falseNegative;
    }

    /**
     * Number of Loose True Positive instances.
     *
     * @return double representing the loose true positive instances
     */
    public double getLooseTruePositive() {
        return looseTruePositive;
    }

    /**
     * Number of Loose False Positive instances.
     *
     * @return double representing number of loose false positive instances
     */
    public double getLooseFalsePositive() {
        return looseFalsePositive;
    }

    /**
     * Number of Loose False Negative Instances.
     *
     * @return double representing number of loose false negative instances.
     */
    public double getLooseFalseNegative() { return looseFalseNegative; }
}
