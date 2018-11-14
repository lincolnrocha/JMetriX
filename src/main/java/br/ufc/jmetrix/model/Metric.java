package br.ufc.jmetrix.model;

public enum Metric {
	//Exception Handling Flow Metrics
	PLoC("EHF_PLoC", "Protected Lines of Code", "The number of lines of code of all protected blocks of a class."),
	PBCL("EHF_PBCL", "Protected Blocks in Control Loops", "The number of protected blocks declared inside a control loop statement."),
	SPEF("EHF_SPEF", "Start Points of Exceptional Flow", "The number of exceptions raised within a class."),
	PBEF("EHF_PBEF", "Protected Blocks of Exceptional Flow", "The number of protected blocks of a class."),
	PBHF("EHF_PBHF", "Protected Branches of Handling Flow", "The number of handlers attached to each protected region in a class."),
	SBHF("EHF_SBHF", "Signaling Branches of Handling Flow", "The number of exceptions signaled by a class."),
	CAHF("EHF_CAHF", "Cleanup Actions of Handling Flow", "The number of cleanup blocks attached to each protected region in a class."),
	TSPEF("EHF_TSPEF", "Types of Start Points of Exceptional Flow", "The distinct number of exception types raised within a class."),
	TPBHF("EHF_TPBHF", "Types of Protected Branches of Handling Flow", "The distinct number of exception types caught by handlers attached to each protected region in a class."),
	TSBHF("EHF_TSBHF", "Types of Signaling Branches of Handling Flow", "The distinct number of exception types signaled by a class."),
	PBCC("EHF_PBCC", "Protected Block Cyclomatic Complexity", "The cyclomatic complexity of statements declared inside the protected blocks."),
	ISMC("EHF_ISMC", "Invocation of Signaler Methods per Class", "The number of invoked methods by a class that signals exceptions."),
	IMPB("EHF_IMPB", "Invoked Methods in Protected Blocks", "The number of invoked methods inside protected blocks of a class."),
	IMHB("EHF_IMHB", "Invoked Methods in Handling Blocks", "The number of invoked methods inside handling blocks of a class."),	
	
	//Exception Handling Bad Smells Metrics
	HLoC("EHS_HLoC", "Handling Lines of Code", "The number of lines of code of all handling blocks of a class."),
	CLoC("EHS_CLoC", "Cleanup Lines of Code", "The number of lines of code of all cleanup blocks of a class."),
	NoRCB("EHS_NoRCB", "Number of Raisings in Cleanup Blocks", "The number of exception raisings in cleanup blocks."),
	NoSKS("EHS_NoSKS", "Number of Signaling the Kitchen Sink", "The number of signalers that signals more then one exception type."),
	NoCRN("EHS_NoCRN", "Number of Catch and Return Null", "The number of handlers that catches an exception an returns null."),
	NoCA("EHS_NoCA", "Number of Catch and Abort", "The number of handlers that catches an exception and exits."),
	NoOC("EHS_NoOC", "Number of Over-Catches", "The number of handlers that catches multiple exception types."),
	NoOCA("EHS_NoOCA", "Number of Over-Catches and Abort", "The number of handlers that catches multiple exception types and exits."),
	NoGH("EHS_NoGH", "Number of Generic Handlings", "The number of handlers in a class who catches RuntimeException, Exception, Error, or Throwable from java.lang package."),
	NoGS("EHS_NoGS", "Number of Generic Signalings", "The number of signalings in a class who signals RuntimeException, Exception, Error, or Throwable from java.lang. package."),
	NoEH("EHS_NoEH", "Number of Empty Handlings", "The number of empty handlers of a given class."),
	NoDR("EHS_NoDR", "Number of Destructive Remappings", "The number of exception type remappings that no preserving the exception stack trace."),
	NoCI("EHS_NoCI", "Number of Catch and Ignore", "The number of handlers who catches an exception and do not uses it anymore in the handler block."),
	NoNPB("EHS_NoNPB", "Number of Nested Protected Blocks", "The number of protected block declared inside an existent protected block."),

	
	// Chidamber and Kemerer (C&K) Metrics
	
	WMC("CK_WMC", "Weighted Methods per Class", ""), 
	DIT("CK_DIT", "Depth of Inheritance Tree", ""), 
	NOC("CK_NOC", "Number of Children", ""), 
	CBO("CK_CBO", "Coupling Between Objects", ""), 
	RFC("CK_RFC", "Response For Class", ""),
	LCOM("CK_LCOM", "Lack of Cohesion of Methods v1", ""),
	
	//Other OO Metrics
	LoC("OO_LoC", "Lines of Code", ""),
	LCOM2("OO_LCOM2", "Lack of Cohesion of Methods v2", ""), 
	LCOM3("OO_LCOM3", "Lack of Cohesion of Methods v3", ""),
	Ca("OO_Ca", "Fan in = Afferent Coupling", ""), 
	Ce("OO_Ce", "Fan out = Efferent Coupling", ""),
	
	NoA("OO_NoA", "Number of Attributes", ""),
	NoDA("OO_NoDA", "Number of Declared Attributes", ""),
	NoIA("OO_NoIA", "Number of Inherited Attributes", ""),
	NoPA("OO_NoPA", "Number of Public Attributes", ""),
	NoPRA("OO_NoPRA", "Number of Private Attributes", ""),
	
	NoM("OO_NoM", "Number of Methods", ""),
	NoDM("OO_NoDM", "Number Declared of Methods", ""),
	NoIM("OO_NoIM", "Number of Inherited Methods", ""),
	NoPM("OO_NoPM", "Number of Public Methods", ""),
	NoPRM("OO_NoPRM", "Number of Private Methods", ""),
	

	//Info Metrics
	EHMU("EHMU", "Exception Handling Usage", ""),
	BUG("BUG", "Has Bug", ""),
	NoBUG("NoBUG", "Number of Bugs", "");
	
	private final String shortName;

	private final String fullName;
	
	private final String description;
	
	Metric(String shortName, String fullName, String description) {
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getDescription() {
		return description;
	}
}
