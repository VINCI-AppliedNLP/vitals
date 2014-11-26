
/* First created by JCasGen Tue Nov 25 15:55:05 CST 2014 */
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

/** 
 * Updated by JCasGen Tue Nov 25 15:55:05 CST 2014
 * @generated */
public class Range_Type extends Pattern_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Range_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Range_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Range(addr, Range_Type.this);
  			   Range_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Range(addr, Range_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Range.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.vitals.types.Range");
 
  /** @generated */
  final Feature casFeat_value1;
  /** @generated */
  final int     casFeatCode_value1;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getValue1(int addr) {
        if (featOkTst && casFeat_value1 == null)
      jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.Range");
    return ll_cas.ll_getRefValue(addr, casFeatCode_value1);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue1(int addr, int v) {
        if (featOkTst && casFeat_value1 == null)
      jcas.throwFeatMissing("value1", "gov.va.vinci.vitals.types.Range");
    ll_cas.ll_setRefValue(addr, casFeatCode_value1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value2;
  /** @generated */
  final int     casFeatCode_value2;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getValue2(int addr) {
        if (featOkTst && casFeat_value2 == null)
      jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.Range");
    return ll_cas.ll_getRefValue(addr, casFeatCode_value2);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue2(int addr, int v) {
        if (featOkTst && casFeat_value2 == null)
      jcas.throwFeatMissing("value2", "gov.va.vinci.vitals.types.Range");
    ll_cas.ll_setRefValue(addr, casFeatCode_value2, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Range_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_value1 = jcas.getRequiredFeatureDE(casType, "value1", "uima.tcas.Annotation", featOkTst);
    casFeatCode_value1  = (null == casFeat_value1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value1).getCode();

 
    casFeat_value2 = jcas.getRequiredFeatureDE(casType, "value2", "uima.tcas.Annotation", featOkTst);
    casFeatCode_value2  = (null == casFeat_value2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value2).getCode();

  }
}



    