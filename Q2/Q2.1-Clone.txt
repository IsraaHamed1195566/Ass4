
	public XSSFSheet cloneSheet(int sheetIndex, String newSheetName) {
		validateSheetsList(sheets);//check if sheets list is empty list
		validateSheetIndex(sheetIndex);//check if sheets index is exsist at sheets list
		XSSFSheet _sourceSheet = sheets.get(sheetIndex);//get target sheet
		newSheetName = createSheetNewName(_sourceSheet, newSheetName);//create new name for cloned sheet not exsist already
		XSSFSheet _clonedSheet = createSheet(newSheetName);//create sheet
		XSSFDrawing _reDrawingClonedSheet = CopySheetRelations(_sourceSheet, _clonedSheet);//get relations with out cloned sheet to re-create sheet cloned already 
		_clonedSheet = PackageSheetRelationships(_sourceSheet, _clonedSheet);//get external relations
		_clonedSheet = cloneFromSourceToNewSheet(_sourceSheet, _clonedSheet);//get data from source and put it in new created  sheet
		CTWorksheet _workSheetsUnset = _clonedSheet.getCTWorksheet();//get work sheet to check which is service is found 
		validateWorkSheet(_workSheetsUnset);// check there are work sheet being getten
		_workSheetsUnset = workSheetServicesUnsupported(_workSheetsUnset);//mainpulate unsetLegacyDrawing and unsetPageSetup
		_clonedSheet.setSelected(false);
		_clonedSheet = cloneSheetDrawing(_sourceSheet, _clonedSheet, _workSheetsUnset, _reDrawingClonedSheet); // clone the sheet drawing along with its relationships

		return _clonedSheet;
	}

	private String createSheetNewName(XSSFSheet _sourceSheet, String newSheetName) {

		if (newSheetName != null || newSheetName != " ") {//if have name check if is exsist
			validateSheetName(newSheetName);
			return newSheetName;

		} else {//if does not have name create new name of source sheet 

			String srcName = _sourceSheet.getSheetName();
			newSheetName = getUniqueSheetName(srcName);
			return newSheetName;
		}
		
	}

	private XSSFDrawing CopySheetRelations(XSSFSheet _sourceSheet, XSSFSheet _clonedSheet) {
		// copy sheet's relations
		List<RelationPart> rels = _sourceSheet.getRelationParts();
		// if the sheet being cloned has a drawing then remember it and re-create it too
		XSSFDrawing _reDrawingClonedSheet = null;
		for (RelationPart rp : rels) {
			POIXMLDocumentPart r = rp.getDocumentPart();
			// do not copy the drawing relationship, it will be re-created
			if (r instanceof XSSFDrawing) {
				_reDrawingClonedSheet = (XSSFDrawing) r;
				continue;
			}

			addRelation(rp, _clonedSheet);
		}
		return _reDrawingClonedSheet;
	}

	private XSSFSheet PackageSheetRelationships(XSSFSheet _sourceSheet, XSSFSheet _clonedSheet) {
		try {
			for (PackageRelationship pr : _sourceSheet.getPackagePart().getRelationships()) {
				if (pr.getTargetMode() == TargetMode.EXTERNAL) {//get relations of external sources
					_clonedSheet.getPackagePart().addExternalRelationship(pr.getTargetURI().toASCIIString(),
							pr.getRelationshipType(), pr.getId());
				}
			}
		} catch (InvalidFormatException e) {
			throw new POIXMLException("Failed to clone sheet", e);
		}
		return _clonedSheet;
	}

	private XSSFSheet cloneFromSourceToNewSheet(XSSFSheet _sourceSheet, XSSFSheet _clonedSheet) {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			_sourceSheet.write(out);
			try (ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray())) {
				_clonedSheet.read(bis);
			}
		} catch (IOException e) {
			throw new POIXMLException("Failed to clone sheet", e);
		}
		return _clonedSheet;
	}

	private CTWorksheet workSheetServicesUnsupported(CTWorksheet workSheet) {
		if (workSheet.isSetLegacyDrawing()) {
			logger.log(POILogger.WARN, "Cloning sheets with comments is not yet supported.");
			workSheet.unsetLegacyDrawing();
		}
		if (workSheet.isSetPageSetup()) {
			logger.log(POILogger.WARN, "Cloning sheets with page setup is not yet supported.");
			workSheet.unsetPageSetup();
		}
		return workSheet;
	}

	private XSSFSheet cloneSheetDrawing(XSSFSheet _sourceSheet, XSSFSheet _clonedSheet, CTWorksheet workSheet,
			XSSFDrawing _reDrawingClonedSheet) {

		if (_reDrawingClonedSheet != null) {// re-creating, it is not the first time the sheet being cloned
			if (workSheet.isSetDrawing()) {
				// unset the existing reference to the drawing,
				// so that subsequent call of _clonedSheet.createDrawingPatriarch() will create a
				// new one
				workSheet.unsetDrawing();
			}
			XSSFDrawing clonedDg = _clonedSheet.createDrawingPatriarch();
			// copy drawing contents
			clonedDg.getCTDrawing().set(_reDrawingClonedSheet.getCTDrawing());

			clonedDg = _clonedSheet.createDrawingPatriarch();

			// Clone drawing relations
			List<RelationPart> srcRels = _sourceSheet.createDrawingPatriarch().getRelationParts();
			for (RelationPart rp : srcRels) {
				addRelation(rp, clonedDg);
			}
		}
		return _clonedSheet;
	}

	private void validateSheetsList(List<XSSFSheet> sheets) {
		if (sheets.size() == 0) {
			throw new POIXMLException("There is no sheets");
		}
	}

	private void validateWorkSheet(CTWorksheet workSheet) {
		if (workSheet == null) {
			throw new POIXMLException("There is no sheets for drawing");
		}
	}