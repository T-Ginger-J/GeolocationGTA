package com.example.geolocationgta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database name and version
    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_LOCATIONS = "location";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    // SQL query to create the location table
    private static final String CREATE_TABLE_LOCATIONS =
            "CREATE TABLE " + TABLE_LOCATIONS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_LATITUDE + " REAL, " +
                    COLUMN_LONGITUDE + " REAL" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOCATIONS);
        insertInitialLocations(db); // Insert GTA locations
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    // Method to insert initial locations
    private void insertInitialLocations(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        String[] locations = {
                "Oshawa Centre, 419 King St W, Oshawa, ON",
                "Pickering Town Centre, 1355 Kingston Rd, Pickering, ON",
                "Scarborough Town Centre, 300 Borough Dr, Scarborough, ON",
                "Eaton Centre, 220 Yonge St, Toronto, ON",
                "Square One Shopping Centre, 100 City Centre Dr, Mississauga, ON",
                "Bramalea City Centre, 25 Peel Centre Dr, Brampton, ON",
                "Pacific Mall, 4300 Steeles Ave E, Markham, ON",
                "Vaughan Mills, 1 Bass Pro Mills Dr, Vaughan, ON",
                "Richmond Hill Centre, 10268 Yonge St, Richmond Hill, ON",
                "Sherway Gardens, 25 The West Mall, Etobicoke, ON",
                "Yorkdale Shopping Centre, 3401 Dufferin St, Toronto, ON",
                "Toronto Pearson International Airport, 6301 Silver Dart Dr, Mississauga, ON",
                "Toronto Zoo, 2000 Meadowvale Rd, Toronto, ON",
                "The Distillery Historic District, 55 Mill St, Toronto, ON",
                "Rogers Centre, 1 Blue Jays Way, Toronto, ON",
                "Royal Ontario Museum, 100 Queens Park, Toronto, ON",
                "High Park, 1873 Bloor St W, Toronto, ON",
                "Humber College, 205 Humber College Blvd, Etobicoke, ON",
                "CF Fairview Mall, 1800 Sheppard Ave E, Toronto, ON",
                "Scarborough Bluffs Park, 61 Undercliff Dr, Scarborough, ON",
                "Casa Loma, 1 Austin Terrace, Toronto, ON",
                "University of Toronto, 27 King's College Cir, Toronto, ON",
                "St. Lawrence Market, 93 Front St E, Toronto, ON",
                "Canada's Wonderland, 1 Canada's Wonderland Drive, Vaughan, ON",
                "Billy Bishop Toronto City Airport, Toronto, ON",
                "Aga Khan Museum, 77 Wynford Dr, North York, ON",
                "Harbourfront Centre, 235 Queens Quay W, Toronto, ON",
                "Royal Conservatory of Music, 273 Bloor St W, Toronto, ON",
                "Ontario Science Centre, 770 Don Mills Rd, North York, ON",
                "The Art Gallery of Ontario, 317 Dundas St W, Toronto, ON",
                "Allan Gardens Conservatory, 19 Horticultural Ave, Toronto, ON",
                "CF Toronto Eaton Centre, 220 Yonge St, Toronto, ON",
                "Toronto Islands, Toronto, ON",
                "The Second City Toronto, 51 Mercer St, Toronto, ON",
                "Ontario Place, 955 Lake Shore Blvd W, Toronto, ON",
                "Woodbine Beach, 1675 Lake Shore Blvd E, Toronto, ON",
                "The Royal Ontario Museum, 100 Queens Park, Toronto, ON",
                "Fort York National Historic Site, 250 Fort York Blvd, Toronto, ON",
                "Distillery District, 55 Mill St, Toronto, ON",
                "The Beaches, Toronto, ON",
                "High Park Zoo, 1873 Bloor St W, Toronto, ON",
                "Ontario Place, 955 Lake Shore Blvd W, Toronto, ON",
                "Toronto Dominion Centre, 66 Wellington St W, Toronto, ON",
                "Yonge-Dundas Square, 1 Dundas St E, Toronto, ON",
                "Casa Loma, 1 Austin Terrace, Toronto, ON",
                "Bloor-Yorkville, Toronto, ON",
                "Queen's Park, Toronto, ON",
                "Toronto City Hall, 100 Queen St W, Toronto, ON",
                "The Royal Alexandra Theatre, 260 King St W, Toronto, ON",
                "Toronto Eaton Centre, 220 Yonge St, Toronto, ON",
                "Toronto Convention Centre, 222 Bremner Blvd, Toronto, ON",
                "Scotiabank Arena, 40 Bay St, Toronto, ON",
                "The ROM, 100 Queens Park, Toronto, ON",
                "The Sick Kids Hospital, 555 University Ave, Toronto, ON",
                "Ryerson University, 350 Victoria St, Toronto, ON",
                "The AGO, 317 Dundas St W, Toronto, ON",
                "Queen's Quay Terminal, 207 Queens Quay W, Toronto, ON",
                "Toronto Railway Museum, 255 Bremner Blvd, Toronto, ON",
                "Distillery District, 55 Mill St, Toronto, ON",
                "Toronto City Hall, 100 Queen St W, Toronto, ON",
                "Harbourfront Centre, 235 Queens Quay W, Toronto, ON",
                "Humber Bay Park, 2000 Lake Shore Blvd W, Toronto, ON",
                "University of Toronto - St. George Campus, 27 King's College Cir, Toronto, ON",
                "Mount Pleasant Cemetery, 375 Mount Pleasant Rd, Toronto, ON",
                "Black Creek Pioneer Village, 1000 Murray Ross Pkwy, Toronto, ON",
                "The Danforth, Toronto, ON",
                "Riverdale Farm, 201 Winchester St, Toronto, ON",
                "Toronto Western Hospital, 399 Bathurst St, Toronto, ON",
                "The Gladstone Hotel, 1214 Queen St W, Toronto, ON",
                "Toronto Zoo, 2000 Meadowvale Rd, Toronto, ON",
                "Ontario Science Centre, 770 Don Mills Rd, North York, ON",
                "Royal Ontario Museum, 100 Queens Park, Toronto, ON",
                "Ripley's Aquarium of Canada, 288 Bremner Blvd, Toronto, ON",
                "St. Lawrence Market, 93 Front St E, Toronto, ON",
                "Toronto Islands, Toronto, ON",
                "Bloor-Yorkville, Toronto, ON",
                "The Beaches, Toronto, ON",
                "High Park, 1873 Bloor St W, Toronto, ON",
                "Toronto Pearson International Airport, 6301 Silver Dart Dr, Mississauga, ON",
                "Casa Loma, 1 Austin Terrace, Toronto, ON",
                "Scarborough Bluffs, 1 Brimley Rd, Scarborough, ON",
                "CF Fairview Mall, 1800 Sheppard Ave E, Toronto, ON",
                "Eaton Centre, 220 Yonge St, Toronto, ON",
                "The Art Gallery of Ontario, 317 Dundas St W, Toronto, ON",
                "The Second City, 51 Mercer St, Toronto, ON",
                "Toronto Dominion Centre, 66 Wellington St W, Toronto, ON",
                "Aga Khan Museum, 77 Wynford Dr, North York, ON",
                "Harbourfront Centre, 235 Queens Quay W, Toronto, ON",
                "Toronto City Hall, 100 Queen St W, Toronto, ON",
                "Yonge-Dundas Square, 1 Dundas St E, Toronto, ON",
                "The Royal Alexandra Theatre, 260 King St W, Toronto, ON",
                "Scotiabank Arena, 40 Bay St, Toronto, ON",
                "Ryerson University, 350 Victoria St, Toronto, ON",
                "Toronto Western Hospital, 399 Bathurst St, Toronto, ON",
                "Royal Conservatory of Music, 273 Bloor St W, Toronto, ON",
                "Bramalea City Centre, 25 Peel Centre Dr, Brampton, ON",
                "Yorkdale Shopping Centre, 3401 Dufferin St, Toronto, ON",
                "Woodbine Beach, 1675 Lake Shore Blvd E, Toronto, ON",
                "The Gladstone Hotel, 1214 Queen St W, Toronto, ON",
                "The Scarborough Town Centre, 300 Borough Dr, Scarborough, ON",
                "Square One Shopping Centre, 100 City Centre Dr, Mississauga, ON",
                "Bramalea City Centre, 25 Peel Centre Dr, Brampton, ON",
                "Shoppers World Brampton, 499 Main St S, Brampton, ON",
                "Vaughan Mills, 1 Bass Pro Mills Dr, Vaughan, ON",
                "Markville Shopping Centre, 5000 Highway 7, Markham, ON",
                "Hillcrest Mall, 9350 Yonge St, Richmond Hill, ON",
                "Upper Canada Mall, 17600 Yonge St, Newmarket, ON",
                "Yorkdale Shopping Centre, 3401 Dufferin St, Toronto, ON",
                "Woodbine Mall, 500 Rexdale Blvd, Etobicoke, ON",
                "Richmond Hill Centre, 10268 Yonge St, Richmond Hill, ON",
                "King's Club Plaza, 1200 King Rd, Burlington, ON",
                "Milton Mall, 55 Ontario St S, Milton, ON",
                "The Shops at Don Mills, 1090 Don Mills Rd, North York, ON",
                "Eglinton Square, 22 Eglinton Ave E, Scarborough, ON",
                "Burlington Mall, 777 Guelph Line, Burlington, ON",
                "North York Sheridan Mall, 2200 Finch Ave W, North York, ON",
                "CF Fairview Mall, 1800 Sheppard Ave E, Toronto, ON",
                "Dixie Outlet Mall, 1250 South Service Rd E, Mississauga, ON",
                "Markham Theatre, 171 Town Centre Blvd, Markham, ON",
                "Erin Mills Town Centre, 5100 Erin Mills Pkwy, Mississauga, ON",
                "South Hill Shopping Centre, 2250 South Millway, Mississauga, ON",
                "Mapleview Centre, 900 Maple Ave, Burlington, ON",
                "The Village at Vaughan Mills, 1 Bass Pro Mills Dr, Vaughan, ON",
                "Royal Windsor Plaza, 1515 Royal Windsor Dr, Oakville, ON",
                "SmartCentres Brampton, 25 Peel Centre Dr, Brampton, ON",
                "Red Maple Shopping Centre, 10380 Yonge St, Richmond Hill, ON",
                "First Markham Place, 3255 Highway 7, Markham, ON",
                "Brampton Golf Club, 7700 Kennedy Rd, Brampton, ON",
                "Niagara Square, 7555 Montrose Rd, Niagara Falls, ON"

        };

        double[][] coordinates = {
                {43.8886, -78.8760},
                {43.8384, -79.0849},
                {43.7768, -79.2574},
                {43.6544, -79.3807},
                {43.5934, -79.6447},
                {43.7157, -79.7279},
                {43.8271, -79.3043},
                {43.8257, -79.5368},
                {43.8770, -79.4402},
                {43.6102, -79.5580},
                {43.7255, -79.4527},
                {43.6777, -79.6248},
                {43.8177, -79.1859},
                {43.6503, -79.3591},
                {43.6414, -79.3894},
                {43.6677, -79.3948},
                {43.6465, -79.4637},
                {43.7305, -79.6085},
                {43.7776, -79.3465},
                {43.7057, -79.2364},
                {43.6780, -79.4094},
                {43.6629, -79.3957},
                {43.6491, -79.3714},
                {43.8430, -79.5390},
                {43.6285, -79.3963},
                {43.7254, -79.3382},
                {43.6386, -79.3826},
                {43.6671, -79.3997},
                {43.7167, -79.3382},
                {43.7138, -79.5985},
                {43.6536, -79.3927},
                {43.6629, -79.3732},
                {43.6544, -79.3807},
                {43.6212, -79.3968},
                {43.6460, -79.3927},
                {43.6365, -79.4120},
                {43.6543, -79.3093},
                {43.6677, -79.3948},
                {43.6387, -79.4060},
                {43.6486, -79.3591},
                {43.6536, -79.3927},
                {43.6629, -79.3732},
                {43.6544, -79.3807},
                {43.6212, -79.3968},
                {43.6460, -79.3927},
                {43.6365, -79.4120},
                {43.6543, -79.3093},
                {43.6677, -79.3948},
                {43.6387, -79.4060},
                {43.6486, -79.3591},
                {43.6544, -79.3807},
                {43.6417, -79.3899},
                {43.6426, -79.3790},
                {43.6677, -79.3948},
                {43.6543, -79.3874},
                {43.6567, -79.3780},
                {43.6536, -79.3927},
                {43.6406, -79.3800},
                {43.6421, -79.3843},
                {43.6493, -79.3591},
                {43.6516, -79.3832},
                {43.6406, -79.3800},
                {43.6341, -79.4851},
                {43.6630, -79.3953},
                {43.7057, -79.3800},
                {43.7670, -79.5216},
                {43.6791, -79.3525},
                {43.6640, -79.3554},
                {43.6513, -79.4090},
                {43.6447, -79.4194},
                {43.8177, -79.1859},
                {43.7254, -79.3382},
                {43.6677, -79.3948},
                {43.6413, -79.3860},
                {43.6491, -79.3714},
                {43.6212, -79.3968},
                {43.6794, -79.2969},
                {43.6764, -79.2890},
                {43.6447, -79.4637},
                {43.6777, -79.6248},
                {43.6780, -79.4094},
                {43.7375, -79.2554},
                {43.7677, -79.3417},
                {43.6544, -79.3807},
                {43.6536, -79.3927},
                {43.6453, -79.3924},
                {43.6417, -79.3816},
                {43.7252, -79.3374},
                {43.6365, -79.4120},
                {43.6386, -79.3826},
                {43.6516, -79.3832},
                {43.6564, -79.3803},
                {43.6426, -79.3790},
                {43.6496, -79.3826},
                {43.6567, -79.3780},
                {43.6715, -79.7713},
                {43.7255, -79.4527},
                {43.6531, -79.4620},
                {43.6583, -79.5076},
                {43.6565, -79.3810},
                {43.7750, -79.2583},
                {43.5890, -79.6445},
                {43.7056, -79.7616},
                {43.6881, -79.7642},
                {43.8333, -79.5233},
                {43.8662, -79.2639},
                {43.8734, -79.4357},
                {44.0703, -79.4360},
                {43.8341, -79.4757},
                {43.7017, -79.5807},
                {43.8949, -79.4330},
                {43.3287, -79.7865},
                {43.5375, -79.7910},
                {43.7303, -79.3375},
                {43.7343, -79.2217},
                {43.3153, -79.7923},
                {43.6555, -79.6392},
                {43.7882, -79.5189},
                {43.6700, -79.7487},
                {43.5912, -79.6134},
                {43.7791, -79.6499},
                {43.6730, -79.4109},
                {43.6454, -79.4450},
                {43.3700, -79.7437},
                {43.8364, -79.4919},
                {43.3930, -79.8187},
                {43.7593, -79.6896},
                {43.8490, -79.5210},
                {43.7160, -79.7006},
                {43.2895, -79.0779}
        };

        for (int i = 0; i < locations.length; i++) {
            values.put(COLUMN_ADDRESS, locations[i]);
            values.put(COLUMN_LATITUDE, coordinates[i][0]);
            values.put(COLUMN_LONGITUDE, coordinates[i][1]);
            db.insert(TABLE_LOCATIONS, null, values);
        }
    }

    // Method to add new location
    public boolean addLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        return db.insert(TABLE_LOCATIONS, null, values) != -1;
    }

    // Method to delete a location by ID
    public boolean deleteLocation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_LOCATIONS, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    // Method to update location details
    public boolean updateLocation(int id, String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        return db.update(TABLE_LOCATIONS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    // Method to get latitude and longitude for a given address
    public Cursor getLocation(String address) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATIONS, new String[]{COLUMN_LATITUDE, COLUMN_LONGITUDE},
                COLUMN_ADDRESS + "=?", new String[]{address}, null, null, null);
    }
}
