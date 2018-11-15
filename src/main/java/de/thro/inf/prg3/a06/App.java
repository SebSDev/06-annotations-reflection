package de.thro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.thro.inf.prg3.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App
{

	public static void main(String[] args)
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Joke.class, new JokeAdapter());
		Gson gson = gsonBuilder.create();

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://api.icndb.com/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();

		ICNDBApi api = retrofit.create(ICNDBApi.class);

		String[] categories = { "nerdy", "explicit"};
		Call<Joke> jCall = api.getRandomJoke(categories);
		Response<Joke> response;

		Joke jk = null;

		try
		{
			response = jCall.execute();
			jk = response.body();
		} catch (IOException e)
		{
			System.out.println(e);
		}

		System.out.println(jk);
	}

}
