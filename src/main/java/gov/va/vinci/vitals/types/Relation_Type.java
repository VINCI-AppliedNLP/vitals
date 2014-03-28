
/* First created by JCasGen Fri Mar 21 14:22:47 CDT 2014 */
package gov.va.vinci.vitals.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Mar 21 14:22:47 CDT 2014
 * @generated */
public class Relation_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Relation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Relation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Relation(addr, Relation_Type.this);
  			   Relation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Relation(addr, Relation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Relation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Relation");
 
  /** @generated */
  final Feature casFeat_Term;
  /** @generated */
  final int     casFeatCode_Term;
  /** @generated */ 
  public String getTerm(int addr) {
        if (featOkTst && casFeat_Term == null)
      jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Term);
  }
  /** @generated */    
  public void setTerm(int addr, String v) {
        if (featOkTst && casFeat_Term == null)
      jcas.throwFeatMissing("Term", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Term, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Value;
  /** @generated */
  final int     casFeatCode_Value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_Value == null)
      jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_Value == null)
      jcas.throwFeatMissing("Value", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Value, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Value2;
  /** @generated */
  final int     casFeatCode_Value2;
  /** @generated */ 
  public String getValue2(int addr) {
        if (featOkTst && casFeat_Value2 == null)
      jcas.throwFeatMissing("Value2", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Value2);
  }
  /** @generated */    
  public void setValue2(int addr, String v) {
        if (featOkTst && casFeat_Value2 == null)
      jcas.throwFeatMissing("Value2", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Value2, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ValueString;
  /** @generated */
  final int     casFeatCode_ValueString;
  /** @generated */ 
  public String getValueString(int addr) {
        if (featOkTst && casFeat_ValueString == null)
      jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ValueString);
  }
  /** @generated */    
  public void setValueString(int addr, String v) {
        if (featOkTst && casFeat_ValueString == null)
      jcas.throwFeatMissing("ValueString", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_ValueString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Concept;
  /** @generated */
  final int     casFeatCode_Concept;
  /** @generated */ 
  public String getConcept(int addr) {
        if (featOkTst && casFeat_Concept == null)
      jcas.throwFeatMissing("Concept", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Concept);
  }
  /** @generated */    
  public void setConcept(int addr, String v) {
        if (featOkTst && casFeat_Concept == null)
      jcas.throwFeatMissing("Concept", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Concept, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Assessment;
  /** @generated */
  final int     casFeatCode_Assessment;
  /** @generated */ 
  public String getAssessment(int addr) {
        if (featOkTst && casFeat_Assessment == null)
      jcas.throwFeatMissing("Assessment", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Assessment);
  }
  /** @generated */    
  public void setAssessment(int addr, String v) {
        if (featOkTst && casFeat_Assessment == null)
      jcas.throwFeatMissing("Assessment", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Assessment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Unit;
  /** @generated */
  final int     casFeatCode_Unit;
  /** @generated */ 
  public String getUnit(int addr) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Unit);
  }
  /** @generated */    
  public void setUnit(int addr, String v) {
        if (featOkTst && casFeat_Unit == null)
      jcas.throwFeatMissing("Unit", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Unit, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Range;
  /** @generated */
  final int     casFeatCode_Range;
  /** @generated */ 
  public String getRange(int addr) {
        if (featOkTst && casFeat_Range == null)
      jcas.throwFeatMissing("Range", "gov.va.vinci.vitals.types.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Range);
  }
  /** @generated */    
  public void setRange(int addr, String v) {
        if (featOkTst && casFeat_Range == null)
      jcas.throwFeatMissing("Range", "gov.va.vinci.vitals.types.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Range, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Relation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Term = jcas.getRequiredFeatureDE(casType, "Term", "uima.cas.String", featOkTst);
    casFeatCode_Term  = (null == casFeat_Term) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Term).getCode();

 
    casFeat_Value = jcas.getRequiredFeatureDE(casType, "Value", "uima.cas.String", featOkTst);
    casFeatCode_Value  = (null == casFeat_Value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Value).getCode();

 
    casFeat_Value2 = jcas.getRequiredFeatureDE(casType, "Value2", "uima.cas.String", featOkTst);
    casFeatCode_Value2  = (null == casFeat_Value2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Value2).getCode();

 
    casFeat_ValueString = jcas.getRequiredFeatureDE(casType, "ValueString", "uima.cas.String", featOkTst);
    casFeatCode_ValueString  = (null == casFeat_ValueString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ValueString).getCode();

 
    casFeat_Concept = jcas.getRequiredFeatureDE(casType, "Concept", "uima.cas.String", featOkTst);
    casFeatCode_Concept  = (null == casFeat_Concept) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Concept).getCode();

 
    casFeat_Assessment = jcas.getRequiredFeatureDE(casType, "Assessment", "uima.cas.String", featOkTst);
    casFeatCode_Assessment  = (null == casFeat_Assessment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Assessment).getCode();

 
    casFeat_Unit = jcas.getRequiredFeatureDE(casType, "Unit", "uima.cas.String", featOkTst);
    casFeatCode_Unit  = (null == casFeat_Unit) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Unit).getCode();

 
    casFeat_Range = jcas.getRequiredFeatureDE(casType, "Range", "uima.cas.String", featOkTst);
    casFeatCode_Range  = (null == casFeat_Range) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Range).getCode();

  }
}



    