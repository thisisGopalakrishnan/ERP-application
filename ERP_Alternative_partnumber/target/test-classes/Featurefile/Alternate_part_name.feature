	Feature: Excel Comparison

Scenario: Compare two different Excel sheets with specific rules
  Given Login as a QA on ERP page
  When Open issue module and fill the form and export  "D:\\testing materials\\Partnumber_comparison\\partnumber_compare.xlsx" the excel sheet "Sheet1"
  And Compare the Excel sheet
  Then close the browser 
