#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;


public class ${plugin_class_name}Data extends BaseStepData implements StepDataInterface {
  // Add any execution-specific data here

  public ${plugin_class_name}Data() {
    super();
  }
}
