  //R: Comment what the function does 
  @Override
    protected void onDocumentRead() throws IOException {
        try {
			//R:Use good method and variable names are long and self-descriptive.
            WorkbookDocument doc = WorkbookDocument.Factory.parse(getPackagePart().getInputStream(), DEFAULT_XML_OPTIONS);
			
			//R: avoid global variables it can be accessible and changeable from anywhere in the program, 
			//R: change global variables into parameters and return values
		   this.workbook = doc.getWorkbook();
			
			//R:large variables scope.
            ThemesTable theme = null;
            Map<String, XSSFSheet> shIdMap = new HashMap<>();
            Map<String, ExternalLinksTable> elIdMap = new HashMap<>();
			
            for(RelationPart rp : getRelationParts()){
				
			//R: can be turned if-else to case statement and exctract code from line 21-36 to method and return the object
                POIXMLDocumentPart p = rp.getDocumentPart();
                if(p instanceof SharedStringsTable) {
                    sharedStringSource = (SharedStringsTable)p;
                } else if(p instanceof StylesTable) {
                    stylesSource = (StylesTable)p;
                } else if(p instanceof ThemesTable) {
                    theme = (ThemesTable)p;
				//R: don't add unused variable Line 28-31 
                } else if(p instanceof CalculationChain) {
                    calcChain = (CalculationChain)p;
                } else if(p instanceof MapInfo) {
                    mapInfo = (MapInfo)p;
                } else if (p instanceof XSSFSheet) {
                    shIdMap.put(rp.getRelationship().getId(), (XSSFSheet)p);
                } else if (p instanceof ExternalLinksTable) {
                    elIdMap.put(rp.getRelationship().getId(), (ExternalLinksTable)p);
                }
            }
            boolean packageReadOnly = (getPackage().getPackageAccess() == PackageAccess.READ);
			//R: exctract code from line 40-47 to method and return the object
            if (stylesSource == null) {
                // Create Styles if it is missing
                if (packageReadOnly) {
                    stylesSource = new StylesTable();
                } else {
                    stylesSource = (StylesTable)createRelationship(XSSFRelation.STYLES, this.xssfFactory);
                }
            }
            stylesSource.setWorkbook(this);
			//R: Handel Error befor send it as parameter if it isn't null
            stylesSource.setTheme(theme);
			//R: exctract code from line 52-59 to method and return the object
            if (sharedStringSource == null) {
                // Create SST if it is missing
                if (packageReadOnly) {
                    sharedStringSource = new SharedStringsTable();
                } else {
                    sharedStringSource = (SharedStringsTable)createRelationship(XSSFRelation.SHARED_STRINGS, this.xssfFactory);
                }
            }

            // Load individual sheets. The order of sheets is defined by the order
            //  of CTSheet elements in the workbook
			//R: Handel Error befor send list size as parameter if it isn't empty line 64 && 72
            sheets = new ArrayList<>(shIdMap.size());
            //noinspection deprecation
            for (CTSheet ctSheet : this.workbook.getSheets().getSheetArray()) {
                parseSheet(shIdMap, ctSheet);
            }

            // Load the external links tables. Their order is defined by the order
            //  of CTExternalReference elements in the workbook
			//R: exctract code from line 73-83 to method and return the object
            externalLinks = new ArrayList<>(elIdMap.size());
            if (this.workbook.isSetExternalReferences()) {
                for (CTExternalReference er : this.workbook.getExternalReferences().getExternalReferenceArray()) {
                    ExternalLinksTable el = elIdMap.get(er.getId());
                    if(el == null) {
                        logger.log(POILogger.WARN, "ExternalLinksTable with r:id " + er.getId()+ " was defined, but didn't exist in package, skipping");
                        continue;
                    }
                    externalLinks.add(el);
                }
            }

            // Process the named ranges
            reprocessNamedRanges();
			//R:catch specific sub classes 
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }
