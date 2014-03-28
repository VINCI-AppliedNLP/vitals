
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
public class Regex_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Regex_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Regex_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Regex(addr, Regex_Type.this);
  			   Regex_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Regex(addr, Regex_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Regex.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Regex");
 
  /** @generated */
  final Feature casFeat_Pattern;
  /** @generated */
  final int     casFeatCode_Pattern;
  /** @generated */ 
  public String getPattern(int addr) {
        if (featOkTst && casFeat_Pattern == null)
      jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.Regex");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Pattern);
  }
  /** @generated */    
  public void setPattern(int addr, String v) {
        if (featOkTst && casFeat_Pattern == null)
      jcas.throwFeatMissing("Pattern", "gov.va.vinci.vitals.types.Regex");
    ll_cas.ll_setStringValue(addr, casFeatCode_Pattern, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Groups;
  /** @generated */
  final int     casFeatCode_Groups;
  /** @generated */ 
  public int getGroups(int addr) {
        if (featOkTst && casFeat_Groups == null)
      jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Groups);
  }
  /** @generated */    
  public void setGroups(int addr, int v) {
        if (featOkTst && casFeat_Groups == null)
      jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    ll_cas.ll_setRefValue(addr, casFeatCode_Groups, v);}
    
   /** @generated */
  public String getGroups(int addr, int i) {
        if (featOkTst && casFeat_Groups == null)
      jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i);
  }
   
  /** @generated */ 
  public void setGroups(int addr, int i, String v) {
        if (featOkTst && casFeat_Groups == null)
      jcas.throwFeatMissing("Groups", "gov.va.vinci.vitals.types.Regex");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Groups), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Regex_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Pattern = jcas.getRequiredFeatureDE(casType, "Pattern", "uima.cas.String", featOkTst);
    casFeatCode_Pattern  = (null == casFeat_Pattern) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Pattern).getCode();

 
    casFeat_Groups = jcas.getRequiredFeatureDE(casType, "Groups", "uima.cas.StringArray", featOkTst);
    casFeatCode_Groups  = (null == casFeat_Groups) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Groups).getCode();

  }
}



    