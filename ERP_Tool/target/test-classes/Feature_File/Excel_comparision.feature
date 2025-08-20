Feature: Excel Comparison

Scenario: Compare two different Excel sheets with specific rules
  Given Login as a QA on ERP page
  When Open issue module and fill the form and export  "E:\New folder\Downloads\e-CAM0M31_TOF_CUMI3300_MOD_H02R1_Rev E1.0_Assembly_BOM.xlsx" the excel sheet "BoM"
  And Compare the Excel sheet
  Then close the browser
