
/* First created by JCasGen Mon Mar 31 19:30:41 CDT 2014 */
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
 * Updated by JCasGen Mon Mar 31 19:30:41 CDT 2014
 * @generated */
public class AnnotationPattern_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotationPattern_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotationPattern_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotationPattern(addr, AnnotationPattern_Type.this);
  			   AnnotationPattern_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotationPattern(addr, AnnotationPattern_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AnnotationPattern.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.AnnotationPattern");
 
  /** @generated */
  final Feature casFeat_anchor;
  /** @generated */
  final int     casFeatCode_anchor;
  /** @generated */ 
  public int getAnchor(int addr) {
        if (featOkTst && casFeat_anchor == null)
      jcas.throwFeatMissing("anchor", "gov.va.vinci.vitals.types.AnnotationPattern");
    return ll_cas.ll_getRefValue(addr, casFeatCode_anchor);
  }
  /** @generated */    
  public void setAnchor(int addr, int v) {
        if (featOkTst && casFeat_anchor == null)
      jcas.throwFeatMissing("anchor", "gov.va.vinci.vitals.types.AnnotationPattern");
    ll_cas.ll_setRefValue(addr, casFeatCode_anchor, v);}
    
  
 
  /** @generated */
  final Feature casFeat_anchorPattern;
  /** @generated */
  final int     casFeatCode_anchorPattern;
  /** @generated */ 
  public String getAnchorPattern(int addr) {
        if (featOkTst && casFeat_anchorPattern == null)
      jcas.throwFeatMissing("anchorPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return ll_cas.ll_getStringValue(addr, casFeatCode_anchorPattern);
  }
  /** @generated */    
  public void setAnchorPattern(int addr, String v) {
        if (featOkTst && casFeat_anchorPattern == null)
      jcas.throwFeatMissing("anchorPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    ll_cas.ll_setStringValue(addr, casFeatCode_anchorPattern, v);}
    
  
 
  /** @generated */
  final Feature casFeat_target;
  /** @generated */
  final int     casFeatCode_target;
  /** @generated */ 
  public int getTarget(int addr) {
        if (featOkTst && casFeat_target == null)
      jcas.throwFeatMissing("target", "gov.va.vinci.vitals.types.AnnotationPattern");
    return ll_cas.ll_getRefValue(addr, casFeatCode_target);
  }
  /** @generated */    
  public void setTarget(int addr, int v) {
        if (featOkTst && casFeat_target == null)
      jcas.throwFeatMissing("target", "gov.va.vinci.vitals.types.AnnotationPattern");
    ll_cas.ll_setRefValue(addr, casFeatCode_target, v);}
    
  
 
  /** @generated */
  final Feature casFeat_targetPattern;
  /** @generated */
  final int     casFeatCode_targetPattern;
  /** @generated */ 
  public String getTargetPattern(int addr) {
        if (featOkTst && casFeat_targetPattern == null)
      jcas.throwFeatMissing("targetPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return ll_cas.ll_getStringValue(addr, casFeatCode_targetPattern);
  }
  /** @generated */    
  public void setTargetPattern(int addr, String v) {
        if (featOkTst && casFeat_targetPattern == null)
      jcas.throwFeatMissing("targetPattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    ll_cas.ll_setStringValue(addr, casFeatCode_targetPattern, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pattern;
  /** @generated */
  final int     casFeatCode_pattern;
  /** @generated */ 
  public String getPattern(int addr) {
        if (featOkTst && casFeat_pattern == null)
      jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pattern);
  }
  /** @generated */    
  public void setPattern(int addr, String v) {
        if (featOkTst && casFeat_pattern == null)
      jcas.throwFeatMissing("pattern", "gov.va.vinci.vitals.types.AnnotationPattern");
    ll_cas.ll_setStringValue(addr, casFeatCode_pattern, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AnnotationPattern_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_anchor = jcas.getRequiredFeatureDE(casType, "anchor", "uima.tcas.Annotation", featOkTst);
    casFeatCode_anchor  = (null == casFeat_anchor) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_anchor).getCode();

 
    casFeat_anchorPattern = jcas.getRequiredFeatureDE(casType, "anchorPattern", "uima.cas.String", featOkTst);
    casFeatCode_anchorPattern  = (null == casFeat_anchorPattern) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_anchorPattern).getCode();

 
    casFeat_target = jcas.getRequiredFeatureDE(casType, "target", "uima.tcas.Annotation", featOkTst);
    casFeatCode_target  = (null == casFeat_target) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_target).getCode();

 
    casFeat_targetPattern = jcas.getRequiredFeatureDE(casType, "targetPattern", "uima.cas.String", featOkTst);
    casFeatCode_targetPattern  = (null == casFeat_targetPattern) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_targetPattern).getCode();

 
    casFeat_pattern = jcas.getRequiredFeatureDE(casType, "pattern", "uima.cas.String", featOkTst);
    casFeatCode_pattern  = (null == casFeat_pattern) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pattern).getCode();

  }
}



    