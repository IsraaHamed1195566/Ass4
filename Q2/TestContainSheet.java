    @Test
    public void testContainsSheet() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Test removing a sheet maintains the named ranges correctly
        XSSFWorkbook _worBookTest = new XSSFWorkbook();
        _worBookTest.createSheet("createdSheet#1");
        _worBookTest.createSheet("createdSheet#2");

        XSSFName createdSheetName1 = _worBookTest.createName();
        createdSheetName1.setNameName("createdSheetName1");
        createdSheetName1.setSheetIndex(0);

        XSSFName createdSheetName2 = _worBookTest.createName();
        createdSheetName2.setNameName("createdSheetName2");
        createdSheetName2.setSheetIndex(1);
        Method _WorBookMethodTest = XSSFWorkbook.class.getDeclaredMethod("containsSheet",String.class,Integer.class);
        _WorBookMethodTest.setAccessible(true);
        boolean  _containsSheet=(boolean) _WorBookMethodTest.invoke(_worBookTest,"createdSheetName2",0);
        assertTrue(_containsSheet);

        _worBookTest.close();
    }
    @Test
    public void testNotContainsSheet() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Test removing a sheet maintains the named ranges correctly
        XSSFWorkbook _worBookTest = new XSSFWorkbook();
        _worBookTest.createSheet("createdSheet#1");
        _worBookTest.createSheet("createdSheet#2");

        XSSFName createdSheetName1 = _worBookTest.createName();
        createdSheetName1.setNameName("createdSheetName1");
        createdSheetName1.setSheetIndex(0);

        XSSFName createdSheetName2 = _worBookTest.createName();
        createdSheetName2.setNameName("createdSheetName2");
        createdSheetName2.setSheetIndex(1);
        Method _WorBookMethodTest = XSSFWorkbook.class.getDeclaredMethod("containsSheet",String.class,Integer.class);
        _WorBookMethodTest.setAccessible(true);
        boolean  _containsSheet=(boolean) _WorBookMethodTest.invoke(_worBookTest,"createdSheetName3",0);
        assertFalse(_containsSheet);

        _worBookTest.close();
    }