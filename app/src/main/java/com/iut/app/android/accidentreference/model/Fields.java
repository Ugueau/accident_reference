package com.iut.app.android.accidentreference.model;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields implements Serializable {

    @SerializedName("agg")
    @Expose
    private String agg;
    @SerializedName("type_de_collision")
    @Expose
    private String typeDeCollision;
    @SerializedName("vosp")
    @Expose
    private String vosp;
    @SerializedName("circ")
    @Expose
    private String circ;
    @SerializedName("infra")
    @Expose
    private String infra;
    @SerializedName("tbg")
    @Expose
    private int tbg;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("nbimplique")
    @Expose
    private int nbimplique;
    @SerializedName("categorie_de_route")
    @Expose
    private String categorieDeRoute;
    @SerializedName("situ")
    @Expose
    private String situ;
    @SerializedName("col")
    @Expose
    private String col;
    @SerializedName("trace_en_plan")
    @Expose
    private String traceEnPlan;
    @SerializedName("atm")
    @Expose
    private String atm;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("pr")
    @Expose
    private String pr;
    @SerializedName("situation_de_l_accident")
    @Expose
    private String situationDeLAccident;
    @SerializedName("lum")
    @Expose
    private String lum;
    @SerializedName("coord")
    @Expose
    private List<Float> coord;
    @SerializedName("intersection")
    @Expose
    private String intersection;
    @SerializedName("agglomeration")
    @Expose
    private String agglomeration;
    @SerializedName("grav")
    @Expose
    private float grav;
    @SerializedName("org")
    @Expose
    private String org;
    @SerializedName("condition_atmospherique")
    @Expose
    private String conditionAtmospherique;
    @SerializedName("tindm")
    @Expose
    private int tindm;
    @SerializedName("geoflamatch")
    @Expose
    private String geoflamatch;
    @SerializedName("tbl")
    @Expose
    private int tbl;
    @SerializedName("catr")
    @Expose
    private String catr;
    @SerializedName("dep")
    @Expose
    private int dep;
    @SerializedName("lumiere")
    @Expose
    private String lumiere;
    @SerializedName("regime_de_circulation")
    @Expose
    private String regimeDeCirculation;
    @SerializedName("profil_en_long")
    @Expose
    private String profilEnLong;
    @SerializedName("ttue")
    @Expose
    private int ttue;
    @SerializedName("codeinsee")
    @Expose
    private String codeinsee;
    @SerializedName("pr1")
    @Expose
    private String pr1;
    @SerializedName("int")
    @Expose
    private String _int;
    @SerializedName("voie")
    @Expose
    private String voie;
    @SerializedName("v1")
    @Expose
    private String v1;
    @SerializedName("nbv")
    @Expose
    private String nbv;
    @SerializedName("departement")
    @Expose
    private String departement;
    @SerializedName("organisme")
    @Expose
    private String organisme;
    @SerializedName("prof")
    @Expose
    private String prof;
    @SerializedName("numac")
    @Expose
    private int numac;

    public String getAgg() {
        return agg;
    }

    public void setAgg(String agg) {
        this.agg = agg;
    }

    public String getTypeDeCollision() {
        return typeDeCollision;
    }

    public void setTypeDeCollision(String typeDeCollision) {
        this.typeDeCollision = typeDeCollision;
    }

    public String getVosp() {
        return vosp;
    }

    public void setVosp(String vosp) {
        this.vosp = vosp;
    }

    public String getCirc() {
        return circ;
    }

    public void setCirc(String circ) {
        this.circ = circ;
    }

    public String getInfra() {
        return infra;
    }

    public void setInfra(String infra) {
        this.infra = infra;
    }

    public int getTbg() {
        return tbg;
    }

    public void setTbg(int tbg) {
        this.tbg = tbg;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public int getNbimplique() {
        return nbimplique;
    }

    public void setNbimplique(int nbimplique) {
        this.nbimplique = nbimplique;
    }

    public String getCategorieDeRoute() {
        return categorieDeRoute;
    }

    public void setCategorieDeRoute(String categorieDeRoute) {
        this.categorieDeRoute = categorieDeRoute;
    }

    public String getSitu() {
        return situ;
    }

    public void setSitu(String situ) {
        this.situ = situ;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getTraceEnPlan() {
        return traceEnPlan;
    }

    public void setTraceEnPlan(String traceEnPlan) {
        this.traceEnPlan = traceEnPlan;
    }

    public String getAtm() {
        return atm;
    }

    public void setAtm(String atm) {
        this.atm = atm;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getSituationDeLAccident() {
        return situationDeLAccident;
    }

    public void setSituationDeLAccident(String situationDeLAccident) {
        this.situationDeLAccident = situationDeLAccident;
    }

    public String getLum() {
        return lum;
    }

    public void setLum(String lum) {
        this.lum = lum;
    }

    public List<Float> getCoord() {
        return coord;
    }

    public void setCoord(List<Float> coord) {
        this.coord = coord;
    }

    public String getIntersection() {
        return intersection;
    }

    public void setIntersection(String intersection) {
        this.intersection = intersection;
    }

    public String getAgglomeration() {
        return agglomeration;
    }

    public void setAgglomeration(String agglomeration) {
        this.agglomeration = agglomeration;
    }

    public float getGrav() {
        return grav;
    }

    public void setGrav(float grav) {
        this.grav = grav;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getConditionAtmospherique() {
        return conditionAtmospherique;
    }

    public void setConditionAtmospherique(String conditionAtmospherique) {
        this.conditionAtmospherique = conditionAtmospherique;
    }

    public int getTindm() {
        return tindm;
    }

    public void setTindm(int tindm) {
        this.tindm = tindm;
    }

    public String getGeoflamatch() {
        return geoflamatch;
    }

    public void setGeoflamatch(String geoflamatch) {
        this.geoflamatch = geoflamatch;
    }

    public int getTbl() {
        return tbl;
    }

    public void setTbl(int tbl) {
        this.tbl = tbl;
    }

    public String getCatr() {
        return catr;
    }

    public void setCatr(String catr) {
        this.catr = catr;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public String getLumiere() {
        return lumiere;
    }

    public void setLumiere(String lumiere) {
        this.lumiere = lumiere;
    }

    public String getRegimeDeCirculation() {
        return regimeDeCirculation;
    }

    public void setRegimeDeCirculation(String regimeDeCirculation) {
        this.regimeDeCirculation = regimeDeCirculation;
    }

    public String getProfilEnLong() {
        return profilEnLong;
    }

    public void setProfilEnLong(String profilEnLong) {
        this.profilEnLong = profilEnLong;
    }

    public int getTtue() {
        return ttue;
    }

    public void setTtue(int ttue) {
        this.ttue = ttue;
    }

    public String getCodeinsee() {
        return codeinsee;
    }

    public void setCodeinsee(String codeinsee) {
        this.codeinsee = codeinsee;
    }

    public String getPr1() {
        return pr1;
    }

    public void setPr1(String pr1) {
        this.pr1 = pr1;
    }

    public String getInt() {
        return _int;
    }

    public void setInt(String _int) {
        this._int = _int;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getNbv() {
        return nbv;
    }

    public void setNbv(String nbv) {
        this.nbv = nbv;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public int getNumac() {
        return numac;
    }

    public void setNumac(int numac) {
        this.numac = numac;
    }

}