# File format information
## Input files
When you choose to read parameters from a file, the file should have following format:   
`transRate#RecoveryRate#GridSizeM#GridSizeN#numOfSims#simTime#animationSpeed#x1,y1;x2,y2;x3,y3...`  
where `transRate` and `RecoveryRate` are rational numbers and the rest are integers. `animationSpeed` needs to be an integer from 1 to 30.  
`x1,y1;x2,y2;x3,y3...` is a list of pairs of coordinates separated by semicolon of initially infected cells.
## Output files
When you choose to save simulation data to a file, the generated file has the following format (an example file):

    Initial infected cells:; 17,17;0,0;3,12;;
    transRate: 0.3; recoveryRate: 0.2; grid dimensions: 35 35
    time;S;I;R
    0;1224.0;1.0;0.0
    1;1223.0;2.0;0.0
    ....
It is a semi-colon separated file which can be loaded into programs like Microsoft Excel to generate a table.
