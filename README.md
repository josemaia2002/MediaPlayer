# Projeto de Linguagens de Programação II
### Identificação Pessoal
**Autores:** *Davi Matias Soares Genuino*, *José Maia da Silva Neto*

**Emails:** *<davimatiassg@gmail.com>*, *<maianeto2014@gmail.com>*

**Turma:** *T01*

## Implementação de um Media Player

﻿## Media Player installation instructions 



## Prerequisites
- Ensure you have Java Development Kit (JDK) installed on your system.
- Download and install Eclipse IDE.



## Project Import
1. Open Eclipse IDE.
2. Click on `File` -> `Import...`.
3. Select `General` -> `Existing Projects into Workspace` and click `Next`.
4. Choose the root directory of your JavaFX project and click `Finish`.



## Configure JavaFX Library
1. Right-click on your project in the Project Explorer.
2. Navigate to `Build Path` -> `Configure Build Path...`.
3. In the Libraries tab, click `Modulepath`, then `Add Library...`.
4. Choose `JavaFX SDK` and follow the prompts to set up the library.



## Run Configuration
1. Right-click on your main class file (containing the `main` method).
2. Select `Run As` -> `Java Application`.



## Troubleshooting
- If you encounter "JavaFX runtime components are missing" errors:
  - Ensure the JavaFX library is correctly configured.
  - Check the VM arguments in the run configuration:
    ```
    --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media
    ```
  - Adjust the path to match your local JavaFX SDK installation.



## Additional Notes
- Make sure your Eclipse IDE has the e(fx)clipse plugin installed for better JavaFX support.

