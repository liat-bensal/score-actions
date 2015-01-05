package org.openscore.content.ssh.utils.simulator.elements;

import org.openscore.content.ssh.utils.simulator.ScriptLines;

public class AlwaysOn {
    Expect expect;
    Send send;

    public static AlwaysOn getInstance(ScriptLines script, char[] newLineCharacter) throws Exception {
        AlwaysOn command = new AlwaysOn();
        if(script != null) {
            String line = script.getCurrentLine();
            line = line.substring(line.indexOf(" "));
            command.setCommand(Send.getInstance(line, newLineCharacter));

            if (!script.nextLine())
                throw new Exception("Always statement started on last line: always statements must have 'on' starting the next line");
            line = script.getCurrentLine();
            if (!line.startsWith("on"))
                throw new Exception("always statements must have 'on' starting the next line");
            line = line.substring(line.indexOf(" "));
            command.setExpect(Expect.getInstance(line));
        }
        return command;
    }

    public void setCommand(Send command) {
        this.send = command;
    }

    public char[] get() {
        if(send != null ) {
            return send.get();
        } else {
            return new char[]{};
        }
    }

    public boolean match(String toMatch, long timeout) throws Exception {
        return expect != null && expect.match(toMatch, timeout);
    }

    public void setExpect(Expect command) {
        this.expect = command;
    }
}