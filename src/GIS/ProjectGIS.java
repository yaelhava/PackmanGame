package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

	/**
	 * this class represents a number of csv files, that consist of layers.
	 * @author yael hava and naama hartuv
	 */

public class ProjectGIS implements GIS_project{
	
	private Set<GIS_layer> set = new HashSet<GIS_layer>();
	private ProjectMetaData pmd;
	
	@Override
	public boolean add(GIS_layer arg0) {
		return set.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		return set.addAll(arg0);
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return set.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return set.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return set.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return set.retainAll(arg0);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return set.toArray(arg0);
	}

	/**
	 * @return pmd - the data of the project

	 */
	
	@Override
	public Meta_data get_Meta_data() {		
		return pmd;
	}

}
