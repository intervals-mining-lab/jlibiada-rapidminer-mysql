package rapidminer.Bioinformatics;

import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.Operator;
import com.rapidminer.operator.OperatorDescription;
import com.rapidminer.operator.UserError;
import com.rapidminer.operator.ports.InputPort;
import com.rapidminer.parameter.*;
import libiada.DataStorage.mysql.Chain.SQLChainWithCharactWriter;
import libiada.DataStorage.mysql.Chain.SQLCharacteristicWriter;
import libiada.DataStorage.mysql.Connector;
import libiada.DataStorage.mysql.SQLQuery;
import libiada.FastChainAlgorithms.FastChain.FastChain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 23.08.11
 * Time: 15:42
 */
public class ExportGeneticsMySQLRM extends Operator {
    private InputPort inPort = getInputPorts().createPort("data", ExampleSet.class);
    private static final String PROTEINE_NAME = "Proteine attribute name";
    private static final String CHAIN_TYPE = "Chain type";
    private static final String START_POSITION = "Start position";
    private static final String END_POSITION = "End position";
    private static final String SERVER = "Server";
    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";
    private static final String OTHER_CHAR = "Other characteristic name:";
    private static final String ORGANISM = "Organism: ";

    public ExportGeneticsMySQLRM(OperatorDescription description) {
        super(description);
    }

    @Override
    public void doWork() {
        ExampleSet set;
        Attributes attributes;
        try {
            set = inPort.getData(ExampleSet.class);
            attributes = set.getAttributes();
            String serverName = getParameter(SERVER);
            String login = getParameter(LOGIN);
            String password = getParameter(PASSWORD);
            Connector connector = new Connector("com.mysql.jdbc.Driver", serverName, login, password);
            SQLQuery query = new SQLQuery(connector.getStatement());
            for (Example example : set) {
                FastChain chain = new FastChain(example.getValueAsString(attributes.get("Chain")));

                String type = example.getValueAsString(attributes.get(getParameter(CHAIN_TYPE)));
                String proteine = example.getValueAsString(attributes.get(getParameter(PROTEINE_NAME)));
                Double start_pos = Double.parseDouble(example.getValueAsString(attributes.get(getParameter(START_POSITION))));
                Double end_pos = Double.parseDouble(example.getValueAsString(attributes.get(getParameter(END_POSITION))));
                String otherAttr = example.getValueAsString(attributes.get(getParameter(OTHER_CHAR)));
                String organism = example.getValueAsString(attributes.get(getParameter(ORGANISM)));

                ChainToDB(query, chain, type, proteine, start_pos, end_pos, otherAttr, organism);
            }
        } catch (UserError userError) {
            System.err.println("getDataError()");
        } catch (Exception e) {
            System.err.println("Exception");
            e.printStackTrace();
        }
    }

    private void ChainToDB(SQLQuery query, FastChain chain, String type, String proteine, double startPos, double endPos, String otherAttr, String organism) throws Exception {
        SQLChainWithCharactWriter writer =  new SQLChainWithCharactWriter(query);
        int chIndex = writer.write(chain, type);

        otherCharactToDB(chIndex, query, proteine, startPos, endPos, organism);
        customCharactToDB(chIndex, query, getParameter(OTHER_CHAR), otherAttr);
    }

    private void customCharactToDB(int chIndex, SQLQuery query, String otherAttr, String value) {
        SQLCharacteristicWriter characteristicWriter = new SQLCharacteristicWriter(query);
        characteristicWriter.write(chIndex, value, otherAttr, "Common", "string");
    }



    private void otherCharactToDB(int chIndex, SQLQuery query, String proteine, double start_pos, double end_pos, String organism) throws UndefinedParameterError {
        SQLCharacteristicWriter characteristicWriter = new SQLCharacteristicWriter(query);

        characteristicWriter.write(chIndex, proteine, "Proteine", "Bioinformatics", "string");
        characteristicWriter.write(chIndex, organism, "Organism", "Bioinformatics", "string");
        characteristicWriter.write(chIndex, start_pos, "Start pos", "Bioinformatics", "int");
        characteristicWriter.write(chIndex, end_pos, "End pos", "Bioinformatics", "int");
    }

    @Override
    public List<ParameterType> getParameterTypes() {
        List<ParameterType> types = super.getParameterTypes();
        types.add(new ParameterTypeAttribute(PROTEINE_NAME, "Proteine attribute: ", inPort));
        types.add(new ParameterTypeAttribute(ORGANISM, "Organism: ", inPort));
        types.add(new ParameterTypeAttribute(CHAIN_TYPE, "Chain type attribute: ", inPort));
        types.add(new ParameterTypeAttribute(START_POSITION, "Start position attribute: ", inPort));
        types.add(new ParameterTypeAttribute(END_POSITION, "End position attribute: ", inPort));

        types.add(new ParameterTypeString(SERVER, "Server: ", "jdbc:mysql://localhost:3306/researchings"));
        types.add(new ParameterTypeString(LOGIN, "Login: ", "root"));
        types.add(new ParameterTypePassword(PASSWORD, "Password: "));
        
        types.add(new ParameterTypeString(OTHER_CHAR, "Other characteristic: ", "IsGene"));

        return types;
    }
}
