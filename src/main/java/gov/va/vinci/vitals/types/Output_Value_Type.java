
/* First created by JCasGen Sat May 30 13:54:40 CDT 2020 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat May 30 13:54:40 CDT 2020
 * @generated */
public class Output_Value_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Output_Value.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Output_Value");
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_valueAnnotation;
  /** @generated */
  final int     casFeatCode_valueAnnotation;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getValueAnnotation(int addr) {
        if (featOkTst && casFeat_valueAnnotation == null)
      jcas.throwFeatMissing("valueAnnotation", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_valueAnnotation);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValueAnnotation(int addr, int v) {
        if (featOkTst && casFeat_valueAnnotation == null)
      jcas.throwFeatMissing("valueAnnotation", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setRefValue(addr, casFeatCode_valueAnnotation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_concept;
  /** @generated */
  final int     casFeatCode_concept;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getConcept(int addr) {
        if (featOkTst && casFeat_concept == null)
      jcas.throwFeatMissing("concept", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_concept);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConcept(int addr, String v) {
        if (featOkTst && casFeat_concept == null)
      jcas.throwFeatMissing("concept", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_concept, v);}
    
  
 
  /** @generated */
  final Feature casFeat_unit;
  /** @generated */
  final int     casFeatCode_unit;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getUnit(int addr) {
        if (featOkTst && casFeat_unit == null)
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_unit);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnit(int addr, int v) {
        if (featOkTst && casFeat_unit == null)
      jcas.throwFeatMissing("unit", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setRefValue(addr, casFeatCode_unit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_unitString;
  /** @generated */
  final int     casFeatCode_unitString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getUnitString(int addr) {
        if (featOkTst && casFeat_unitString == null)
      jcas.throwFeatMissing("unitString", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_unitString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setUnitString(int addr, String v) {
        if (featOkTst && casFeat_unitString == null)
      jcas.throwFeatMissing("unitString", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_unitString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_source;
  /** @generated */
  final int     casFeatCode_source;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSource(int addr) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_source);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSource(int addr, String v) {
        if (featOkTst && casFeat_source == null)
      jcas.throwFeatMissing("source", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_source, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timestamp;
  /** @generated */
  final int     casFeatCode_timestamp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTimestamp(int addr) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_timestamp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTimestamp(int addr, int v) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setRefValue(addr, casFeatCode_timestamp, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timestampString;
  /** @generated */
  final int     casFeatCode_timestampString;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTimestampString(int addr) {
        if (featOkTst && casFeat_timestampString == null)
      jcas.throwFeatMissing("timestampString", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_timestampString);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTimestampString(int addr, String v) {
        if (featOkTst && casFeat_timestampString == null)
      jcas.throwFeatMissing("timestampString", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_timestampString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_section;
  /** @generated */
  final int     casFeatCode_section;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSection(int addr) {
        if (featOkTst && casFeat_section == null)
      jcas.throwFeatMissing("section", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getRefValue(addr, casFeatCode_section);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSection(int addr, int v) {
        if (featOkTst && casFeat_section == null)
      jcas.throwFeatMissing("section", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setRefValue(addr, casFeatCode_section, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sectionType;
  /** @generated */
  final int     casFeatCode_sectionType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSectionType(int addr) {
        if (featOkTst && casFeat_sectionType == null)
      jcas.throwFeatMissing("sectionType", "gov.va.vinci.vitals.types.Output_Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sectionType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSectionType(int addr, String v) {
        if (featOkTst && casFeat_sectionType == null)
      jcas.throwFeatMissing("sectionType", "gov.va.vinci.vitals.types.Output_Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_sectionType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Output_Value_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

 
    casFeat_valueAnnotation = jcas.getRequiredFeatureDE(casType, "valueAnnotation", "uima.tcas.Annotation", featOkTst);
    casFeatCode_valueAnnotation  = (null == casFeat_valueAnnotation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_valueAnnotation).getCode();

 
    casFeat_concept = jcas.getRequiredFeatureDE(casType, "concept", "uima.cas.String", featOkTst);
    casFeatCode_concept  = (null == casFeat_concept) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_concept).getCode();

 
    casFeat_unit = jcas.getRequiredFeatureDE(casType, "unit", "uima.tcas.Annotation", featOkTst);
    casFeatCode_unit  = (null == casFeat_unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_unit).getCode();

 
    casFeat_unitString = jcas.getRequiredFeatureDE(casType, "unitString", "uima.cas.String", featOkTst);
    casFeatCode_unitString  = (null == casFeat_unitString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_unitString).getCode();

 
    casFeat_source = jcas.getRequiredFeatureDE(casType, "source", "uima.cas.String", featOkTst);
    casFeatCode_source  = (null == casFeat_source) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_source).getCode();

 
    casFeat_timestamp = jcas.getRequiredFeatureDE(casType, "timestamp", "uima.tcas.Annotation", featOkTst);
    casFeatCode_timestamp  = (null == casFeat_timestamp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timestamp).getCode();

 
    casFeat_timestampString = jcas.getRequiredFeatureDE(casType, "timestampString", "uima.cas.String", featOkTst);
    casFeatCode_timestampString  = (null == casFeat_timestampString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timestampString).getCode();

 
    casFeat_section = jcas.getRequiredFeatureDE(casType, "section", "uima.tcas.Annotation", featOkTst);
    casFeatCode_section  = (null == casFeat_section) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_section).getCode();

 
    casFeat_sectionType = jcas.getRequiredFeatureDE(casType, "sectionType", "uima.cas.String", featOkTst);
    casFeatCode_sectionType  = (null == casFeat_sectionType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sectionType).getCode();

  }
}



    