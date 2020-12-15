 @Override
    public void setSheetName(int sheetIndex, String sheetname) {
		
		//R: Here throwing NullPointerException would be clearer than throwing IllegalArgumentException. 
		//R: because setSheetName have to  check null parameters (null bug) not that if the parameter does not satisy its condion 
		//R: it is better to use try-catch
        if (sheetname == null) {
            throw new IllegalArgumentException( "sheetName must not be null" );
        }

        validateSheetIndex(sheetIndex);
        String oldSheetName = getSheetName(sheetIndex);

        // YK: Mimic Excel and silently truncate sheet names longer than 31 characters
		//R: I think if there are desision can be made with out impacting on user,
		//the programmer have to make them and forcing user to enter no longer than 31 characters
		//so, there is no need to ask user for cutting,the user does not know what is the better for effective software
        if(sheetname.length() > 31) {
            sheetname = sheetname.substring(0, 31);
        }
        WorkbookUtil.validateSheetName(sheetname);

        // Do nothing if no change
		//R:it must be at the begin of the method and must have throwing exception 
        if (sheetname.equals(oldSheetName)) {
            return;
        }

        //R: Check it isn't already taken
		//R: All previous checks are same as this one line 28-30
        if (containsSheet(sheetname, sheetIndex )) {
            throw new IllegalArgumentException( "The workbook already contains a sheet of this name" );
        }

        // Update references to the name
        XSSFFormulaUtils utils = new XSSFFormulaUtils(this);
        utils.updateSheetName(sheetIndex, oldSheetName, sheetname);

        workbook.getSheets().getSheetArray(sheetIndex).setName(sheetname);
    }
