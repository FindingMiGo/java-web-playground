package model;

public class Editor {
    private String editorId;
    private String editorName;
    private String loginPassword;

    public Editor(String editorId, String editorName, String loginPassword) {
        this.editorId = editorId;
        this.editorName = editorName;
        this.loginPassword = loginPassword;
    }

    public String getEditorId() {
        return editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
